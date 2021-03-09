package com.mtv.erp.mybatis.mappers;

import com.mtv.erp.model.Department;
import com.mtv.erp.model.Position;
import com.mtv.erp.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

public interface UserMapper {

    @Insert({"INSERT INTO user (login, password, firstname, lastname, patronymic, number, birthDay, departmentId, positionId, userType) VALUES (#{user.login}, #{user.password}, #{user.firstname}, #{user.lastname}, #{user.patronymic}, #{user.number}, #{user.birthDay}, #{user.department.id}, #{user.position.id}, #{user.userType})"})
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    void insert(@Param("user") User user);

    @Select({"SELECT * FROM user WHERE id = #{id} AND deleted = FALSE"})
    @Results({
            @Result(property = "department", column = "departmentId", javaType = Department.class,
                    one = @One(select = "com.mtv.erp.mybatis.mappers.DepartmentMapper.getById", fetchType = FetchType.EAGER)),
            @Result(property = "position", column = "positionId", javaType = Position.class,
                    one = @One(select = "com.mtv.erp.mybatis.mappers.PositionMapper.getById", fetchType = FetchType.EAGER))
    })
    User getById(@Param("id") int id);
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
