package com.mtv.erp.mybatis.mappers;

import com.mtv.erp.model.Department;
import com.mtv.erp.model.Position;
import com.mtv.erp.model.User1;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserMapper {

    @Insert({"INSERT INTO user (idPf, login, firstname, lastname, patronymic) VALUES (#{user.idPf}, #{user.login}, #{user.firstname}, #{user.lastname}, #{user.patronymic})"})
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    void insert(@Param("user") User1 user);

    @Select({"SELECT * FROM user WHERE id = #{id} AND deleted = FALSE"})
    @Results({
            @Result(property = "department", column = "departmentId", javaType = Department.class,
                    one = @One(select = "com.mtv.erp.mybatis.mappers.DepartmentMapper.getById", fetchType = FetchType.EAGER)),
            @Result(property = "position", column = "positionId", javaType = Position.class,
                    one = @One(select = "com.mtv.erp.mybatis.mappers.PositionMapper.getById", fetchType = FetchType.EAGER))
    })
    User1 getById(@Param("id") int id);


    @Update({"UPDATE user SET login = #{user.login}, firstname = #{user.firstname}, lastname = #{user.lastname}, patronymic = #{user.patronymic} WHERE id = #{user.id}"})
    boolean update(@Param("user") User1 user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    Integer delete(@Param("id") Integer userId);

    @Insert({"<script>",
            "INSERT INTO worker (idPf, login, firstname, lastname, patronymic) VALUES",
            "<foreach item='user' collection= 'list' separator=', '>",
            "(#{user.idPf}, #{user.login}, #{user.firstname}, #{user.lastname}, #{user.patronymic})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    List<User1> insertAll(@Param("list") List<User1> list);

    @Select({"SELECT * FROM user"})
    List<User1> getAll();

//    @Delete({"DELETE FROM session WHERE sessionId = #{sessionId}"})
//    Integer logout(@Param("sessionId") String sessionId);
//
//    @Insert({"INSERT INTO user (login, password, firstName, lastName, patronymic, type) VALUES "
//            + "(#{user.login}, #{user.password}, #{user.firstName}, #{user.lastName}, #{user.patronymic}, #{user.userType})"})
//    @Options(useGeneratedKeys = true, keyProperty = "user.id")
//    void insertUser(@Param("user") User user);
//
//    @Update({"UPDATE user SET password = #{user.password}, firstName = #{user.firstName}, " +
//            "lastName = #{user.lastName}, patronymic = #{user.patronymic} WHERE id = #{user.id}"})
//    void updateUser(@Param("user") User user);
//
//    @Select({"SELECT * FROM user WHERE id = (SELECT userId FROM session WHERE sessionId = #{session})"})
//    User getBySession(String session);
//
//    @Select({"SELECT * FROM session WHERE sessionId = #{sessionId}"})
//    @Results({
//            @Result(property = "sessionId", column = "sessionId"),
//            @Result(property = "user", column = "sessionId", javaType = Session.class,
//                    one = @One(select = "net.thumbtack.school.hospital.mybatis.mappers.UserMapper.getBySession", fetchType = FetchType.EAGER))})
//    Session getSession(@Param("sessionId") String sessionId);
//
//    @Delete("DELETE FROM user WHERE id != 1")
//    Integer clear();
//
//    @Delete("DELETE FROM session")
//    Integer clearSession();
//
//    @Select("SELECT * FROM user WHERE login = #{login}")
//    User getByLogin(String login);
}
