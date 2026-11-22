package baseNoStates.requests;

import baseNoStates.DirectoryAreas;
import baseNoStates.Door;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestRefresh implements Request {
  private static final Logger logger = LoggerFactory.getLogger(RequestRefresh.class);
  private final ArrayList<JSONObject> jsonsDoors = new ArrayList<>();

  @Override
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("doors", new JSONArray(jsonsDoors));
    // jsonDoors has been set previously by process()
    return json;
  }

  @Override
  public String toString() {
    return "RequestRefresh{"
        + jsonsDoors
        + "}";
  }

  // Also this is used to paint the simulator when the page is loaded, and to display
  // doors and readers after passing from locked to propped or propped to locked,
  // pressing the Refresh Request button of the simulator.
  // Also, to quickly test if the partition requests sent by the client app in Flutter
  // works or not, retrieves the state of all the doors so that the simulator can
  // repaint the readers
  @Override
  public void process() {
    for (Door door : DirectoryAreas.getInstance().getAllDoors()) {
      jsonsDoors.add(door.toJson());
    }
  }
}
