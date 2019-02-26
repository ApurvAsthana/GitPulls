package com.sunny.gitpulls.utils;

import android.util.Log;

import com.sunny.gitpulls.ui.splitview.FilesDiffItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FileDiffParseHelper {
    private static final String TAG = "FileDiffParseHelper";

    public ArrayList<FilesDiffItem> getFileDiffListFromJson(String responseFromGetApi) throws JSONException {
        ArrayList<FilesDiffItem> result = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(responseFromGetApi);
        JSONObject object;
        for (int i=0;i<jsonArray.length();i++){
            object = jsonArray.getJSONObject(i);
            result.add(new FilesDiffItem(-1,null,-1,null,true,object.getString("filename"),false));
            fillWithPatchData(object.getString("patch"),result);
        }
        return result;
    }

    private void fillWithPatchData(String patch, ArrayList<FilesDiffItem> result) {
        String[] lines = patch.split("\\n");
        int line1Ctr,line2Ctr;
        String[] firstLineItems = lines[0].split(" ");
        line1Ctr = Integer.parseInt(firstLineItems[1].replace("-","").split(",")[0]);
        line2Ctr = Integer.parseInt(firstLineItems[2].replace("+","").split(",")[0]);
        result.add(new FilesDiffItem(-1,lines[0],-1,null,true,null,false));
        String currItem;
        for (int i=1;i<lines.length;i++){
            currItem = lines[i];
            if(currItem==null||currItem.isEmpty()|| (currItem.charAt(0)!='-' && currItem.charAt(0)!='+' && currItem.charAt(0)!='@')){
                result.add(new FilesDiffItem(line1Ctr,currItem,line2Ctr,currItem,false,null,false));
                line1Ctr++;
                line2Ctr++;
            }else if(currItem.charAt(0)=='@'){
                String[] curLineItems = currItem.split(" ");
                line1Ctr = Integer.parseInt(curLineItems[1].replace("-","").split(",")[0]);
                line2Ctr = Integer.parseInt(curLineItems[2].replace("+","").split(",")[0]);
                result.add(new FilesDiffItem(-1,currItem,-1,null,true,null,false));
            }else{
                Queue<FilesDiffItem> q = new LinkedList<>();
                while(currItem.charAt(0)=='-'){
                    q.add(new FilesDiffItem(line1Ctr,currItem,true));
                    line1Ctr++;
                    i++;
                    if(i<lines.length) {
                        currItem = lines[i];
                    }
                }
                while (!q.isEmpty() && currItem.charAt(0)=='+'){
                    FilesDiffItem qItem = q.poll();
                    qItem.setLineNumberFile2(line2Ctr);
                    qItem.setLineStringFile2(currItem);
                    qItem.setHeader(false);
                    result.add(qItem);
                    line2Ctr++;
                    i++;
                    if(i<lines.length) {
                        currItem = lines[i];
                    }
                }
                while (!q.isEmpty()){
                    FilesDiffItem qItem = q.poll();
                    qItem.setHeader(false);
                    result.add(qItem);
                }
                while(currItem.charAt(0)=='+'){
                    result.add(new FilesDiffItem(-1,null,line2Ctr,currItem,false,null,true));
                    line2Ctr++;
                    i++;
                    if(i<lines.length) {
                        currItem = lines[i];
                    }
                }
            }
        }
    }

}
