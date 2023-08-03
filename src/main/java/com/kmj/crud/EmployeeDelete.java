package com.kmj.crud;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.kmj.common.JDBCTemplate.close;
import static com.kmj.common.JDBCTemplate.getConnection;

public class EmployeeDelete {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();
        Scanner sc = new Scanner(System.in);
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/kmj/mapper/tableQuery.xml"));
            String query = prop.getProperty("deleteEmployee");


            System.out.print("개인정보를 삭제할 사원의 사원번호를 입력하세요.");
            String empId = sc.nextLine();

            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, Integer.parseInt(empId));

            result = pstmt.executeUpdate();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
            close(pstmt);
            close(con);
        }
        System.out.println(result + "명 개인정보 삭제 완료");
    }
}
