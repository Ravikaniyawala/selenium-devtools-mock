package utilities;

import com.google.gson.JsonArray;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import utilities.DataObjects.DataManager;

import java.io.IOException;
import java.util.*;

public class JSONParser {
    public JSONObject jsonObj = null;
    public JSONArray jsonArray = null;

    public JSONParser(String JSONToParse) {
        try {
            this.jsonObj = (JSONObject)JSONValue.parse(JSONToParse);
        } catch (Exception var3) {
            this.jsonArray = (JSONArray)JSONValue.parse(JSONToParse);
        }

    }

    public JSONParser(JSONObject jsonObject) {
        this.jsonObj = jsonObject;
    }

    public JSONParser(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public String getValue(String value) {
        return this.isObject() ? this.jsonObj.get(value).toString() : null;
    }

    public JSONParser getChildJSONObject(String childName) {
        return this.isObject() ? new JSONParser((JSONObject)this.jsonObj.get(childName)) : null;
    }

    public JSONParser getChildJSONArray(String childName) {
        return this.isObject() ? new JSONParser((JSONArray)this.jsonObj.get(childName)) : null;
    }

    public List<JSONParser> getJSONArrayAsList(String childName) {
        try {
            List<JSONParser> list = new ArrayList();
            JSONArray results = (JSONArray)this.jsonObj.get(childName);

            for(int i = 0; i < results.size(); ++i) {
                JSONParser j = new JSONParser((JSONObject)results.get(i));
                list.add(j);
            }

            return list;
        } catch (Exception var6) {
            System.out.println("Node was not a json array.");
            return null;
        }
    }

    public void updateFirstValueOnPath(String node, String value) {
        if (node.contains("/")) {
            List<String> nodeNames = new ArrayList();
            String[] var4 = node.split("/");
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String nodeName = var4[var6];
                nodeNames.add(nodeName);
            }

            this.jsonObj = this.getNode(this.jsonObj, nodeNames, value);
        } else {
            this.jsonObj.put(node, value);
        }

    }

    public void clearJsonArray(String node) {
        this.jsonObj.remove(node);
        this.jsonObj.put(node, new JsonArray());
    }

    public void updateSpecifiedValueOnPath(String node, Object value, int whichToUpdate) {
        if (node.contains("/")) {
            List<String> nodeNames = new ArrayList();
            String[] var5 = node.split("/");
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String nodeName = var5[var7];
                nodeNames.add(nodeName);
            }

            this.jsonObj = this.getNode(this.jsonObj, nodeNames, whichToUpdate, value);
        } else {
            this.jsonObj.put(node, value);
        }

    }

    public String findFirstValueOnPath(String node) {
        if (!node.contains("/")) {
            return this.jsonObj.get(node).toString();
        } else {
            List<String> nodeNames = new ArrayList();
            String[] var3 = node.split("/");
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String nodeName = var3[var5];
                nodeNames.add(nodeName);
            }

            return ((JSONObject)this.findNodes((String)nodeNames.get(nodeNames.size() - 2)).get(0)).get(nodeNames.get(nodeNames.size() - 1)).toString();
        }
    }

    public String findSpecifiedValueOnPath(String node, int whichNode) {
        if (!node.contains("/")) {
            return this.jsonObj.get(node).toString();
        } else {
            List<String> nodeNames = new ArrayList();
            String[] var4 = node.split("/");
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String nodeName = var4[var6];
                nodeNames.add(nodeName);
            }

            return ((JSONObject)this.findNodes((String)nodeNames.get(nodeNames.size() - 2)).get(whichNode)).get(nodeNames.get(nodeNames.size() - 1)).toString();
        }
    }

    private JSONObject getNode(JSONObject j, List<String> node, Object value) {
        String thisNode = (String)node.get(0);
        if (j.containsKey(thisNode)) {
            if (node.size() == 1) {
                j.put(thisNode, value);
            } else {
                node.remove(0);
                System.out.println(" the right node!: " + thisNode);
                String type = j.get(thisNode).getClass().toString();
                if (type.contains("JSONArray")) {
                    JSONArray p = (JSONArray)j.get(thisNode);

                    for(int i = 0; i < p.size(); ++i) {
                        JSONObject jp = (JSONObject)p.get(i);
                        p.set(i, this.getNode(jp, node, value));
                    }

                    j.put(thisNode, p);
                } else if (type.contains("JSONObject")) {
                    JSONObject q = (JSONObject)j.get(thisNode);
                    j.put(thisNode, this.getNode(q, node, value));
                }
            }
        }

        return j;
    }

    private JSONObject getNode(JSONObject j, List<String> node, int nodeNum, Object value) {
        String thisNode = (String)node.get(0);
        if (j.containsKey(thisNode)) {
            if (node.size() == 1) {
                j.put(thisNode, value);
            } else {
                node.remove(0);
                System.out.println(" the right node!: " + thisNode);
                String type = j.get(thisNode).getClass().toString();
                if (type.contains("JSONArray")) {
                    JSONArray p = (JSONArray)j.get(thisNode);
                    JSONObject jp = (JSONObject)p.get(nodeNum);
                    p.set(nodeNum, this.getNode(jp, node, value));
                    j.put(thisNode, p);
                } else if (type.contains("JSONObject")) {
                    JSONObject q = (JSONObject)j.get(thisNode);
                    j.put(thisNode, this.getNode(q, node, value));
                }
            }
        }

        return j;
    }

    private String findValueAnyLevelUniversal(String nodeName) {
        List<HashMap<String, String>> flattenedJSON = flattenValues(this.jsonObj);
        Iterator var3 = flattenedJSON.iterator();

        Map keyValuePair;
        do {
            if (!var3.hasNext()) {
                return "";
            }

            keyValuePair = (Map)var3.next();
        } while(!keyValuePair.containsKey(nodeName));

        return (String)keyValuePair.get(nodeName);
    }

    public String findValueAnyLevel(String nodeName) {
        if (this.jsonArray != null) {
            int i = 0;
            if (i < this.jsonArray.size()) {
                this.jsonObj = (JSONObject)this.jsonArray.get(i);
                return this.findValueAnyLevelUniversal(nodeName);
            } else {
                return "";
            }
        } else {
            return this.findValueAnyLevelUniversal(nodeName);
        }
    }

    public static List<HashMap<String, String>> flattenValues(JSONObject values) {
        List<HashMap<String, String>> flatList = new ArrayList();
        Object[] valuesCollection = values.values().toArray();
        Object[] keysCollection = values.keySet().toArray();

        for(int i = 0; i < valuesCollection.length; ++i) {
            HashMap valuePair = new HashMap();

            String type;
            try {
                type = valuesCollection[i].getClass().toString();
                if (type.equals("class org.json.simple.JSONArray")) {
                    JSONArray ja = (JSONArray)valuesCollection[i];
                    if (ja.size() > 0) {
                        if (ja.get(0).getClass().toString().equals("class java.lang.String")) {
                            valuePair.put(keysCollection[i].toString().trim(), valuesCollection[i].toString().trim());
                            flatList.add(valuePair);
                        } else {
                            flatList.addAll(flattenValues((JSONArray)valuesCollection[i]));
                        }
                    }
                } else if (type.equals("class org.json.simple.JSONObject")) {
                    flatList.addAll(flattenValues((JSONObject)valuesCollection[i]));
                } else {
                    valuePair.put(keysCollection[i].toString().trim(), valuesCollection[i].toString().trim());
                    flatList.add(valuePair);
                }
            } catch (Exception var8) {
                type = "null";
            }
        }

        return flatList;
    }

    public static List<HashMap<String, String>> flattenValues(JSONArray jsonArray) {
        List<HashMap<String, String>> flatList = new ArrayList();

        for(int q = 0; q < jsonArray.size(); ++q) {
            flatList.addAll(flattenValues((JSONObject)jsonArray.get(q)));
        }

        return flatList;
    }

    public List<JSONObject> findNodes(String nodeName) {
        List<JSONObject> nodes = new ArrayList();
        if (this.jsonArray != null) {
            for(int i = 0; i < this.jsonArray.size(); ++i) {
                if (this.jsonArray.get(i).getClass().toString().contains("JSONObject")) {
                    JSONParser jObj = new JSONParser((JSONObject)this.jsonArray.get(i));
                    nodes.addAll(jObj.findNodesObject(nodeName));
                }
            }
        } else {
            nodes.addAll(this.findNodesObject(nodeName));
        }

        return nodes;
    }

    public List<JSONObject> removeNodes(List<JSONObject> obj, String nodeName, int value){
        Iterator<JSONObject> iterator = obj.listIterator();
        while (iterator.hasNext()){
            JSONObject x = iterator.next();
            //System.out.println(x.toJSONString());
            if(Integer.parseInt(new JSONParser(x).findValueAnyLevel(nodeName))==value){
                iterator.remove();
            }
        }
        return obj;
    }

    public List<JSONObject> filterNodes(List<JSONObject> obj,String nodeName,String value){
        Iterator<JSONObject> iterator = obj.listIterator();
        while (iterator.hasNext()){
            JSONObject x = iterator.next();
            //System.out.println(x.toJSONString());
            if(!new JSONParser(x).findValueAnyLevel(nodeName).equals(value)){
                iterator.remove();
            }
        }
        return obj;
    }

    public static boolean isValidNode(JSONObject obj, String nodeName, int value){
        return (Integer.parseInt(new JSONParser(obj).findValueAnyLevel(nodeName))==value);
    }

    private List<JSONObject> findNodesObject(String nodeName) {
        List<JSONObject> nodes = new ArrayList();
        Object[] valuesCollection = this.jsonObj.values().toArray();
        Object[] keysCollection = this.jsonObj.keySet().toArray();

        for(int q = 0; q < keysCollection.length; ++q) {
            try {
                if (keysCollection[q].equals(nodeName)) {
                    if (valuesCollection[q].getClass().toString().contains("JSONArray")) {
                        JSONArray valueArray = (JSONArray)valuesCollection[q];

                        for(int p = 0; p < valueArray.size(); ++p) {
                            nodes.add((JSONObject)valueArray.get(p));
                        }
                    } else if (valuesCollection[q].getClass().toString().contains("JSONObject")) {
                        nodes.add((JSONObject)valuesCollection[q]);
                    }
                } else {
                    JSONParser j2;
                    if (valuesCollection[q].getClass().toString().contains("JSONArray")) {
                        j2 = new JSONParser((JSONArray)valuesCollection[q]);
                        nodes.addAll(j2.findNodes(nodeName));
                    } else if (valuesCollection[q].getClass().toString().contains("JSONObject")) {
                        j2 = new JSONParser((JSONObject)valuesCollection[q]);
                        nodes.addAll(j2.findNodes(nodeName));
                    }
                }
            } catch (Exception var8) {
            }
        }

        return nodes;
    }

    private static List<HashMap<String, Object>> flattenObjects(JSONObject values) {
        List<HashMap<String, Object>> flatlist = new ArrayList();
        Object[] valuesCollection = values.values().toArray();
        Object[] keysCollection = values.keySet().toArray();

        for(int i = 0; i < valuesCollection.length; ++i) {
            String type;
            try {
                type = valuesCollection[i].getClass().toString();
            } catch (Exception var9) {
                type = "null";
            }

            HashMap<String, Object> jsonObjectData = new HashMap();
            jsonObjectData.put(keysCollection[i].toString(), valuesCollection[i]);
            flatlist.add(jsonObjectData);
            if (type.equals("class org.json.simple.JSONArray")) {
                JSONArray jsonArray = (JSONArray)valuesCollection[i];
                if (jsonArray.size() > 0) {
                    for(int q = 0; q < jsonArray.size(); ++q) {
                        flatlist.addAll(flattenObjects((JSONObject)jsonArray.get(q)));
                    }
                }
            } else if (type.equals("class org.json.simple.JSONObject")) {
                flatlist.addAll(flattenObjects((JSONObject)valuesCollection[i]));
            }
        }

        return flatlist;
    }

    public void writeOutJSONFile(String path) {
        try {
            FileHelpers.writeOutFile(this.jsonObj.toJSONString(), path);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public String toJsonString() {
        return this.jsonObj.toJSONString();
    }

    public JSONParser getAtIndex(int index) {
        return !this.isObject() ? new JSONParser((JSONObject)this.jsonArray.get(index)) : null;
    }

    public JSONObject getObjectAtIndex(int index) {
        return !this.isObject() ? (JSONObject)this.jsonArray.get(index) : null;
    }

    public int size() {
        try {
            return this.jsonArray.size();
        } catch (Exception var2) {
            return 0;
        }
    }

    public List<Object> findAllObjects(String nodeName) {
        List<Object> values = new ArrayList();
        List<HashMap<String, Object>> flattenedJSON = new ArrayList();
        if (this.jsonArray != null) {
            for(int i = 0; i < this.jsonArray.size(); ++i) {
                JSONObject jsonObj = (JSONObject)this.jsonArray.get(i);
                flattenedJSON.addAll(flattenObjects(jsonObj));
            }
        } else {
            flattenedJSON.addAll(flattenObjects(this.jsonObj));
        }

        Iterator var7 = flattenedJSON.iterator();

        while(var7.hasNext()) {
            HashMap<String, Object> value = (HashMap)var7.next();
            if (value.containsKey(nodeName)) {
                values.add(value.get(nodeName));
            }
        }

        return values;
    }

    public List<String> findAllValues(String nodeName) {
        List<String> values = new ArrayList();
        List<HashMap<String, String>> flattenedJSON = flattenValues(this.jsonObj);
        Iterator var4 = flattenedJSON.iterator();

        while(var4.hasNext()) {
            Map<String, String> keyValuePair = (Map)var4.next();
            if (keyValuePair.containsKey(nodeName)) {
                values.add(keyValuePair.get(nodeName));
            }
        }

        return values;
    }

    public static boolean isObject(Object objectToTest) {
        try {
            JSONObject jsonObject = (JSONObject)objectToTest;
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public boolean isObject() {
        return this.jsonObj != null;
    }

    public boolean isArray() {
        return this.jsonArray != null;
    }

    public int getCountOfElements(String nodeName) {
        int count = 0;
        List<HashMap<String, Object>> flattenedJSON = flattenObjects(this.jsonObj);
        Iterator var4 = flattenedJSON.iterator();

        while(var4.hasNext()) {
            Map<String, Object> keyValuePair = (Map)var4.next();
            if (keyValuePair.containsKey(nodeName)) {
                ++count;
            }
        }

        return count;
    }

    public List<Map<String, String>> createDataMap(String commaDelimitedFields) {
        List<Map<String, String>> mappedJsonArray = new ArrayList();
        String[] fields = commaDelimitedFields.split(",");
        List<List<String>> fieldResults = new ArrayList();
        String[] var5 = fields;
        int var6 = fields.length;

        int q;
        for(q = 0; q < var6; ++q) {
            String field = var5[q];
            fieldResults.add(this.findAllValues(field));
        }

        for(int i = 0; i < ((List)fieldResults.get(0)).size(); ++i) {
            Map<String, String> newMap = new HashMap();

            for(q = 0; q < fields.length; ++q) {
                newMap.put(fields[q], (String) ((List)fieldResults.get(q)).get(i));
            }

            mappedJsonArray.add(newMap);
        }

        return mappedJsonArray;
    }

    /*public String getValueWithJsonPath(String expression) {
        Object value;
        if (this.jsonObj != null) {
            value = JsonPath.parse(this.jsonObj).read(expression, new Predicate[0]);
        } else {
            if (this.jsonObj != null || this.jsonArray.size() != 1) {
                return null;
            }

            value = JsonPath.parse(this.jsonArray.get(0)).read(expression, new Predicate[0]);
        }

        if (value.getClass().toString().equalsIgnoreCase("class net.minidev.json.JSONArray")) {
            net.minidev.json.JSONArray Ja = (net.minidev.json.JSONArray)value;
            value = ((net.minidev.json.JSONArray)value).get(0);
        }

        return value.toString();
    }*/

    public List<Map<String, String>> createMapFromJsonArray() {
        List<Map<String, String>> mappedArray = new ArrayList();
        Iterator var2 = this.jsonArray.iterator();

        while(var2.hasNext()) {
            Object obj = var2.next();
            JSONObject arrayValue = (JSONObject)obj;
            Map<String, String> map = new HashMap();
            String[] keySet = (String[])((String[])arrayValue.keySet().toArray(new String[arrayValue.size()]));
            String[] var7 = keySet;
            int var8 = keySet.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                String key = var7[var9];
                String value = arrayValue.get(key).toString();
                map.put(key, value);
            }

            mappedArray.add(map);
        }

        return mappedArray;
    }

    public String findArrayValueByKey(String keyName, String keyValue, String fieldtoFind) {
        List<Map<String, String>> mappedArray = this.createMapFromJsonArray();
        Iterator var5 = mappedArray.iterator();

        Map value;
        do {
            if (!var5.hasNext()) {
                return null;
            }

            value = (Map)var5.next();
        } while(!value.containsKey(keyName) || !((String)value.get(keyName)).equalsIgnoreCase(keyValue) || !value.containsKey(fieldtoFind));

        return (String)value.get(fieldtoFind);
    }

    public static boolean isValuePresent(JSONParser jsonParser,String key,String value) {
        return jsonParser.findValueAnyLevel(key).contains(value);
    }

    public static String getProdDetails(JSONParser product, String var){
        try{
            return String.valueOf(product.getValue(var));
        }catch (NullPointerException n){
            return "true";
        }
    }
}