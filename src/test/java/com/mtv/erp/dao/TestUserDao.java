package com.mtv.erp.dao;

import com.google.gson.Gson;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;
import com.mtv.erp.mybatis.daoimpl.UserDaoImpl;
import com.mtv.erp.utils.Planfix;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestUserDao {
    private final UserDao userDao = new UserDaoImpl();

//    @Test
//    public void testInsert() throws ServerException {
//        User user = userDao.insert(new User("mikhail", "Михаил", "Баранцев", "Сергеевич"));
//        Assertions.assertNotEquals(0, user.getId());
//    }

    @Test
    public void XmlTest() throws IOException, JAXBException {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String cmd = "getUsers";
            Runtime rt = Runtime.getRuntime() ;
            Process p = rt.exec("./planfix/PlanFix.Console.exe " + cmd) ;
            InputStream in = p.getInputStream();
            OutputStream out = p.getOutputStream ();
            InputStream err = p.getErrorStream() ;
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null){
                str.append(line);
            }
            p.destroy() ;
            System.out.println(str);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            List<User> users = gson.fromJson(str.toString(), listType);
            users.get(0);
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }

        ResponseEntity<String> response = restTemplate.getForEntity("http://192.168.0.123:90/getUsers", String.class);
        //String utf8String= new String(response.getBody().getBytes("WINDOWS-1251"), StandardCharsets.UTF_8);
        System.out.println(response.getBody());
        Gson gson = new Gson();
        List<User> users =  gson.fromJson(response.getBody().replace("<html><body>", "").replace("</body></html>", ""), List.class);
        users.get(0);

       // System.out.println(utf8String);
//        System.out.println(response.getBody());
//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.readValue(response.getBody(), UsersResponse.class);
 //       ObjectMapper objectMapper = new ObjectMapper();
//        String value = objectMapper.writeValueAsString(jsonNode);
//        System.out.println(value);
    }

    @Test
    public void TestGetProjects() throws ServerException {
        Planfix planfix = new Planfix("getProjects active");
        System.out.println(planfix.getProjects());
    }

}
