package com.future.experience.gugou;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *  给一个用字符串表示的json，比如 '{"a": {"b": [{"c": "d"}, {"e": "f"}] }}', 再给一个input，比如 a.b[1].c.e，计算输出的结果，
 *  这个例子的话输出结果就是"e"。assume所有的输入都是合法的
 *
 *
 *  {} wraps a object.
 *  [] wraps an array
 *  An object consists of either a key/value pair, or a list of object, the value could be a string or an object.
 *
 *  Go through characters one by one
 *  - '{', start a new object, push current object into stack, and new an object.
 *  - '}', end a object, pop an object from stack and assign it to the object value.
 *  - '"', wraps a key or a string value. find the key and value.
 *  - ':', after the key and before a value
 *  - ',', in an array and separate objects in the array. created an object and add to objList
 *  - '[', start an array, create an array for current object.
 *  - ']', end an array,  end the array and assign it to current object.
 */
public class JsonParser {
    private class JsonObj {
        public String key;

        public String strVal;

        public JsonObj objVal;

        public List<JsonObj> objList;
    }

    public JsonObj parse(String jsonStr) {
        JsonObj root = new JsonObj();
        Stack<JsonObj> stack = new Stack<>();
        int  p2 = 0;
        JsonObj curObj = root;
        boolean isArray = false;
        while (p2 < jsonStr.length()) {
            char ch = jsonStr.charAt(p2);
            if(ch == '{') {
                stack.push(curObj);
                curObj = new JsonObj();
            } else if(ch == '}') {
                if(!stack.isEmpty()) {
                    JsonObj obj = stack.pop();
                    if(isArray) {
                        obj.objList.add(curObj);
                        curObj = new JsonObj();
                    } else {
                        obj.objVal = curObj;
                        curObj = obj;
                    }


                }
            } else if(ch == '"') {
                int p = p2 + 1;
                while (p < jsonStr.length() && jsonStr.charAt(p) != '"') {
                    p++;
                }
                if(curObj.key == null) {
                    curObj.key = jsonStr.substring(p2, p);
                } else {
                    curObj.strVal = jsonStr.substring(p2, p);
                }
                p2 = p + 1;
            } else if(ch == ',') {
                if(!stack.isEmpty()) {
                    JsonObj obj = stack.pop();
                    obj.objList.add(curObj);
                    stack.push(obj);
                    curObj = new JsonObj();
                }
            } else if(ch == '[') {
                curObj.objList = new ArrayList<>();
                stack.push(curObj);
                curObj = new JsonObj();
                isArray = true;
            } else if(ch == ']') {
                if(!stack.isEmpty()) {
                    JsonObj obj = stack.pop();
                    obj.objList.add(curObj);
                    curObj = obj;
                }
                isArray = false;
            }
            p2++;
        }
        return root.objVal;
    }

    public static void main(String[] args) {
        JsonParser p = new JsonParser();
        p.parse("{\"a\": {\"b\": [{\"c\": \"d\"}, {\"e\": \"f\"}] }}");
    }
}
