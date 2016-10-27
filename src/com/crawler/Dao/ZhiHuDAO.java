package com.crawler.Dao;

import com.crawler.entity.User;
import java.sql.*;

public class ZhiHuDAO {
    /**
     * 同步方法（获取class的锁）
     * user 插入数据库
     *
     * @param user
     * @throws SQLException
     */
    public synchronized static boolean insertUser(User user) {
        Connection conn = JDBCUtil.getConnection();
        try {
            String colum = "location,business,sex,employment,username,url,agrees,thanks,asks," +
                    "answers,posts,followees,followers,hashId,education";
            String values = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            String sql = "insert into user (" + colum + ") values(" + values + ")";
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getLocation());
            pstmt.setString(2, user.getBusiness());
            pstmt.setString(3, user.getSex());
            pstmt.setString(4, user.getEmployment());
            pstmt.setString(5, user.getUsername());
            pstmt.setString(6, user.getUrl());
            pstmt.setInt(7, user.getAgrees());
            pstmt.setInt(8, user.getThanks());
            pstmt.setInt(9, user.getAsks());
            pstmt.setInt(10, user.getAnswers());
            pstmt.setInt(11, user.getPosts());
            pstmt.setInt(12, user.getFollowees());
            pstmt.setInt(13, user.getFollowers());
            pstmt.setString(14, user.getHashId());
            pstmt.setString(15, user.getEducation());
            //执行插入
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}


