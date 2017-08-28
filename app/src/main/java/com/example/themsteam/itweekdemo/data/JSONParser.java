package com.example.themsteam.itweekdemo.data;

import android.content.Context;
import android.content.res.Resources;

import com.example.themsteam.itweekdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vladimir on 18.08.2017.
 */

public class JSONParser {
    public static List<QRCodeModel> getQRCodeList(Context context) {
        List<QRCodeModel> list = new ArrayList<>();
        try {
            String jsonString = getRawString(R.raw.qr_code_items, context);
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int id = object.getInt("id");
                String code = object.getString("title");
                String note = object.getString("note");
                list.add(new QRCodeModel(id, code, note));
            }
        } catch (JSONException e) {
            return Collections.emptyList();
        }
        return list;
    }


    private static String getRawString(int resourceId, Context context) {
        InputStream is = null;
        try {
            is = context.getResources().openRawResource(resourceId);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            return sb.toString();
        } catch (Resources.NotFoundException | IOException ex) {
            // TODO: Log error
        } finally {
            //close input
            if (is != null) {
                try {
                    is.close();
                }
                catch(IOException ex) {
                    // TODO: log error
                }
            }
        }
        return null;
    }
}
