package com.kmj.crud;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.kmj.common.JDBCConnect.close;
import static com.kmj.common.JDBCConnect.getConnection;

public class EmployeeUpdate {

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        Scanner sc = new Scanner(System.in);

        System.out.println("사원의 직급 및 연봉 정보를 수정하실 수 있습니다.");
        System.out.print("직급번호를 무엇으로 수정할까요? ");
        String jobCode = sc.nextLine();
        System.out.print("연봉단계를 얼마로 수정할까요? ");
        String salLevel = sc.nextLine();
        System.out.print("정보를 수정할 사원의 사원번호는?");
        String empId = sc.nextLine();

        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/kmj/mapper/tableQuery.xml"));
            String query = prop.getProperty("updateEmployee");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, jobCode);
            pstmt.setString(2, salLevel);
            pstmt.setString(3, empId);
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
        System.out.println(result + "명 개인정보 수정 완료");
    }
}
