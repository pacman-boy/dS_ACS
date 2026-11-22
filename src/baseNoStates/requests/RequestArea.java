package baseNoStates.requests;

import baseNoStates.Actions;
import baseNoStates.DirectoryAreas;
import baseNoStates.DirectoryUserGroups;
import baseNoStates.Door;
import baseNoStates.User;
import baseNoStates.areas.Area;
import baseNoStates.visitor.GetDoorsGivingAccessVisitor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Obté totes les portes que donen accés a l'àrea
// i processa una RequestReader individual per a cadascuna.
public class RequestArea implements Request {
  private static final Logger logger = LoggerFactory.getLogger(RequestArea.class);
  private final String credential;
  private final String action;
  private final String areaId;
  private final LocalDateTime now;
  private final ArrayList<RequestReader> requests = new ArrayList<>();
  private String userName;

  public RequestArea(String credential, String action, LocalDateTime now, String areaId) {
    this.credential = credential;
    this.areaId = areaId;
    assert action.equals(Actions.LOCK) || action.equals(Actions.UNLOCK)
        : "invalid action " + action + " for an area request";
    this.action = action;
    this.now = now;
  }

  public String getAction() {
    return action;
  }

  public String getAreaId() {
    return areaId;
  }

  public LocalDateTime getNow() {
    return now;
  }

  public String getUserName() {
    if (userName == null) {
      User user = DirectoryUserGroups.getInstance().findUserByCredential(credential);
      userName = (user != null) ? user.getName() : "unknown";
    }
    return userName;
  }

  public boolean isAuthorized() {
    if (requests.isEmpty()) {
      return false;
    }
    for (RequestReader rd : requests) {
      if (!rd.isAuthorized()) {
        return false;
      }
    }
    return true;
  }

  public ArrayList<RequestReader> getRequests() {
    return requests;
  }

  @Override
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("action", action);
    json.put("areaId", areaId);
    JSONArray jsonRequests = new JSONArray();
    for (RequestReader rd : requests) {
      jsonRequests.put(rd.answerToJson());
    }
    json.put("requestsDoors", jsonRequests);
    return json;
  }

  @Override
  public String toString() {
    String requestsDoorsStr;
    if (requests.isEmpty()) {
      requestsDoorsStr = "";
    } else {
      requestsDoorsStr = requests.toString();
    }
    return "Request{"
        + "credential=" + credential
        + ", action=" + action
        + ", now=" + now
        + ", areaId=" + areaId
        + ", requestsDoors=" + requestsDoorsStr
        + "}";
  }

  @Override
  public void process() { // Lock and Unlock all dors in one Partition
    getUserName(); // inicialitza userName
    Area area = DirectoryAreas.getInstance().findAreaById(areaId);
    if (area != null) {
      GetDoorsGivingAccessVisitor visitor = new GetDoorsGivingAccessVisitor();
      area.acceptVisitor(visitor);
      for (Door door : visitor.getDoors()) {
        RequestReader requestReader = new RequestReader(credential, action, now, door.getId());
        requestReader.process();
        requests.add(requestReader);
      }
    }
  }
}
