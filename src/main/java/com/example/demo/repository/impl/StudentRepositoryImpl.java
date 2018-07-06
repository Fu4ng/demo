package com.example.demo.repository.impl;

import com.example.demo.domain.entity.House;
import com.example.demo.domain.entity.Msg;
import com.example.demo.domain.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sun.nio.cs.ext.MS874;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private static final String SQL_INSERT_USER = "insert into students (username,password,address,phone,sex,birthday) values(?,?,?,?,?,?)";
    private static final String SQL_FIND_USER_NAME = "select * from students where username=?";
    private static final String SQL_FIND_USER_ID = "select id,username,password,address,phone,sex from students where username=?";
    private static final String SQL_SAVE_MSG="insert into msg(stuid,houseid,userid,address) values(?,?,?,?)";
    private static final String SQL_FIND_HOUSE_BY_ID = "select * from house where id =?";
    private static final String SQL_ALL_MSG="select * from msg where stuid = ?";
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<Msg> showAllMsg(long stuid){
        List<Msg> list=new ArrayList<Msg>();
        list = jdbcOperations.query(SQL_ALL_MSG, new RowMapper<Msg>() {
            @Override
            public Msg mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                Msg msg =new Msg();
                msg.setUserid(rs.getLong("userid"));
                msg.setAddress(rs.getString("address"));
                msg.setStuid(rs.getLong("stuid"));
                msg.setHouseid(rs.getLong("houseid"));
                //获取数据库内的见面时间，然后加一天
                Date d = rs.getDate("postTime");
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                c.add(Calendar.DAY_OF_MONTH,1);
                c.add(Calendar.HOUR_OF_DAY,6);
                Date t = c.getTime();  //t是加一天之后的时间
//                DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
//                String s = df.format(t);
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                String s = t.toString();
//                Date k = dateFormat.parse(s);
                msg.setPosttime(t);

                return msg;
            }
        },stuid);
        return list;
    }
    @Override
    public void save(Student user){
        jdbcOperations.update(SQL_INSERT_USER,user.getUserName(),user.getPassword(),user.getAddress(),
                user.getPhone(),user.getSex(),user.getBirthday());
    }

    @Override
    public Student findByUserName(String userName){
        List<Student> list = new ArrayList<Student>();
        list = jdbcOperations.query(SQL_FIND_USER_NAME, new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                Student user = new Student();
                user.setId(rs.getLong("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setSex(rs.getString("sex"));
                user.setBirthday(rs.getString("birthday"));
                return user;
            }

        }, userName);

        if (list.size() >= 1) {
            return list.get(0);
        } else
            return null;
    }


    @Override
    public Student findByUserId(long userId) {
        List<Student> list = new ArrayList<Student>();
        list = jdbcOperations.query(SQL_FIND_USER_ID, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student user = new Student();
                user.setId(rs.getLong("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setSex(rs.getString("sex"));
                user.setBirthday(rs.getString("birthday"));
                return user;
            }
        }, userId);
        if (list.size() >= 1) {
            return list.get(0);
        } else
            return null;


    }

    @Override
    public void msgSave(Msg msg){
        jdbcOperations.update(SQL_SAVE_MSG,msg.getStuid(),msg.getHouseid(),msg.getUserid(),
                msg.getAddress());
    }

    @Override
    public House findHouseById(long id){
        House houseF;
        List<House> list=new ArrayList<House>();
        list = jdbcOperations.query(SQL_FIND_HOUSE_BY_ID, new RowMapper<House>() {
            @Override
            public House mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                House house = new House();
                house.setId(rs.getLong("id"));
                house.setUserId(rs.getLong("userId"));
                house.setAddress(rs.getString("address"));
                house.setDetail(rs.getString("detail"));
                house.setPostTime(rs.getDate("postTime"));
                house.setPrice(rs.getInt("price"));
                house.setType(rs.getString("ty"));
                house.setPnum(rs.getInt("pnum"));
                return house;
            }
        },id);
        houseF = list.get(0);
        return houseF;
    }
}
