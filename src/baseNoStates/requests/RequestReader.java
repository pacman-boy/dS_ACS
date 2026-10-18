package baseNoStates.requests;

import baseNoStates.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void addReason(String reason) {
    reasons.add(reason);
  }


  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
            + "credential=" + credential
            + ", userName=" + userName
            + ", action=" + action
            + ", now=" + now
            + ", doorID=" + doorId
            + ", closed=" + doorClosed
            + ", authorized=" + authorized
            + ", reasons=" + reasons
            + "}";
  }

  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
  public void process() {
    User user = DirectoryUserGroups.findUserByCredential(credential);
    Door door = DirectoryAreas.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";
    authorize(user, door);
    // this sets the boolean authorize a y que parte del codigo comprueba al hcaer reader las acciones de ese grupo?ttribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  private void authorize(User user, Door door) { // Comprobar si el usuario tiene permiso para acceder a la puerta segun su grupo y la accion que quiere realizar.
    if (user == null) { // Compravacion que exista el usuario
      authorized = false;
      addReason("user doesn't exists");
      return;
    }
    // user (Who)

    // ¿User puede hacer la accion ahora? (When)
    if (!user.canDoActionNow(now)) {
      addReason("User cannot do " + action + " now");
      return;
    }

    // ¿User puede estar en el Space(From)? (Where)
    if (!user.canBeInSpace(door.getFromSpace())) {
      addReason("User cannot be in space " + door.getFromSpace().getId());
      return;
    }

    // ¿User puede estar en el Space(To)? (Where)
    if (!user.canBeInSpace(door.getToSpace())) {
      addReason("User cannot be in space " + door.getToSpace().getId());
      return;
    }

    // Comprobación de la acción permitida (What)
    if (!user.canDoAction(action)) {
      addReason("User cannot do " + action);
      return;
    }

    // Si tiene permiso de estar en ambos Spaces
    authorized = true;
  }
}

