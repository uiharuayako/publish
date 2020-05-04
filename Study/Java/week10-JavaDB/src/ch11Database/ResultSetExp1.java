/*
java.sql.Statement stmt = conn.createStatement();
ResultSet r = stmt.executeQuery("SELECT a, b, c FROM Table1");
while (r.next())
{
// 打印当前行的值。
int i = r.getInt("a");
String s = r.getString("b");
float f = r.getFloat("c");
System.out.println("ROW = " + i + " " + s + " " + f);
}
*/
