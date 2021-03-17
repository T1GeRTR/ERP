package com.mtv.erp.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mtv.erp.exception.ErrorCode;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Planfix {
    String cmd;

    public Planfix(String cmd){
        this.cmd = cmd;
    }

    public List<User> getUsers() throws ServerException {
        try {
            Runtime rt = Runtime.getRuntime() ;
            Process p = rt.exec("./planfix/PlanFix.Console.exe " + cmd) ;
            InputStream in = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                str.append(line);
            }
            p.destroy() ;
            System.out.println(str);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            return gson.fromJson(str.toString(), listType);
        }
        catch(Exception exc)
        {
            throw new ServerException(ErrorCode.PLANFIX_CANT_GET_USERS);
        }
    }

    public String getProjects() throws ServerException{
        try {
            Runtime rt = Runtime.getRuntime() ;
            Process p = rt.exec("./planfix/PlanFix.Console.exe " + cmd) ;
            InputStream in = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                str.append(line);
            }
            p.destroy() ;
            System.out.println(str);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            return str.toString();
        }
        catch(Exception exc)
        {
            throw new ServerException(ErrorCode.PLANFIX_CANT_GET_USERS);
        }
    }
}
