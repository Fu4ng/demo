package com.example.demo.repository.impl;

import com.example.demo.domain.entity.House;
import com.example.demo.repository.HouseRepository;
import com.example.demo.web.controller.FormEntity.HouseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HouseRepositoryImpl implements HouseRepository{
    private static final String SQL_SAVE_HOUSE="insert into house (userId,address,detail,price,ty,pnum) value(?,?,?,?,?,?)";
    private static final String SQL_FIND_HOUSE_BY_USERID=
            "select id,userId,address,detail,postTime,price ,ty,pnum from house where userId = ?";
    private static final String SQL_DELETE_HOUSE ="delete from house where id = ?";
    private static final String SQL_FIND_HOUSE_BY_ID = "select * from house where id =?";
    private static final String SQL_RESET_HOUSE="update house set address= ?,detail=?,price=?,ty=?,pnum=? where id = ?";
    private static final String SQL_SHOW_ALL="select * from house";
    @Autowired
    private JdbcOperations jdbcOperations;

    //所有房屋
    @Override
    public List<House> showAll(){
        List<House> list=new ArrayList<House>();
        list = jdbcOperations.query(SQL_SHOW_ALL, new RowMapper<House>() {
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
        });
        return list; }
    //删除记录
    @Override
    public void delete(long id){
        jdbcOperations.update(SQL_DELETE_HOUSE,id);
    }

    //修改记录
    @Override
    public void reset(long id,HouseForm houseForm){
        jdbcOperations.update(SQL_RESET_HOUSE,houseForm.getAddress(),houseForm.getDetail(),
                houseForm.getPrice(),houseForm.getType(),houseForm.getPnum(),id);
    }
    //查询房屋
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


    @Override
    public void save(HouseForm houseForm){
        House house = new House();
        house.setUserId(houseForm.getUserId());
        house.setAddress(houseForm.getAddress());
        house.setDetail(houseForm.getDetail());
        house.setPrice(houseForm.getPrice());
        house.setType(houseForm.getType());
        house.setPnum(houseForm.getPnum());
        jdbcOperations.update(SQL_SAVE_HOUSE,house.getUserId(),house.getAddress(),house.getDetail(),
                house.getPrice(),house.getType(),house.getPnum());
    }


    @Override
    public List<House> findHouseByUserId(Long userId){
        List<House> list=new ArrayList<House>();
        list = jdbcOperations.query(SQL_FIND_HOUSE_BY_USERID, new RowMapper<House>() {
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
        },userId);
        return list;
    }
}
