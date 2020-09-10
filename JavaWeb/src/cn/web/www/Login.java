package cn.web.www;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    MyCon mc=new MyCon();   //将数据库的连接单独放在一个类中,编译后的class文件在同一个文件夹，不用引入类，直接使用
    Connection con;   //存放连接对象
    PreparedStatement ps;   //存放预处理对象
    ResultSet rs;    //存放结果设置对象
    boolean flag=false;
//	String message=null;

    //初始化操作
    public void init() throws ServletException
    {
        // 执行必需的初始化
//	      message = "Hello World";
    }

    /*
     * //连接数据库 //驱动 private static final String
     * DATABASE_DRIVER="oracle.jdbc.driver.OracleDriver"; //网连地址 private static
     * final String DATABASE_URL="jdbc:oracle:thin:@127.0.0.1:1521:taotao"; //用户名
     * private static final String DATABASE_USER="scott"; //密码 private static final
     * String DATABASE_PASS="tiger"; //数据库连接对象:每一个connection对象都描述的是一个用户的连接 private
     * static Connection CONN=null; //创建数据库操作对象 private static Statement STMT=null;
     *//**
     * @see HttpServlet#HttpServlet()
     *//*
     * public Login() throws Exception { //执行连接 Class.forName(DATABASE_DRIVER);
     * //反射出驱动jar包 CONN = DriverManager.getConnection(DATABASE_URL, DATABASE_USER,
     * DATABASE_PASS); //创建数据库操作对象 STMT=CONN.createStatement();
     *
     * }
     */

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置字符编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 想在浏览器上输出，须用打印输出流
        PrintWriter out = response.getWriter();
        // 获取请求方式
//		String method=request.getMethod();

//		//获取表单提交的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        out.println(username);
        out.println(password);
        String sql = "select * from users where username='"+username+"'";
        con=mc.getconn();
        try {
            ps=con.prepareStatement(sql);
        } catch (SQLException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }

        try {
            rs=ps.executeQuery();

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        Cookie name=null;
        Cookie pass=null;
        try {
            if(rs.next()) {
                out.println(password.getClass().toString());
                out.println(rs.getString(3).getClass().toString());
                if(password.equals(rs.getString(3))) {   //判断密码
                    //设置cookie
//					 Cookie name = new Cookie("username",URLEncoder.encode(request.getParameter("username"), "UTF-8")); // 中文转码
                    name = new Cookie("username",username);
                    pass = new Cookie("password",password);
                    // 为两个 Cookie 设置过期日期为 24 小时后
                    name.setMaxAge(60);
                    pass.setMaxAge(60);

                    // 在响应头中添加两个 Cookie;response.addcookie是给浏览器发送一个cookie
                    response.addCookie( name );
                    response.addCookie( pass );

                    out.println(name.getValue());
                    out.println(pass.getValue());
                    //重置cookie值
                    name.setValue("你好世界");
                    out.println(name.getValue());
                    out.println(pass.getValue());

                    out.println(rs.getInt(1) + "-" + rs.getString(2) + "-" + rs.getString(3));
                }else {
                    out.println("密码错误");
                }


            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
//		try {
//			while (rs.next()) {
//				out.print(sql);
//				out.println(rs.getInt(1) + "-" + rs.getString(2) + "-" + rs.getString(3));
//
//			}
//
//		} catch (SQLException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
    public void destroy() {
        try {
            System.out.println("关闭");
            con.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}

