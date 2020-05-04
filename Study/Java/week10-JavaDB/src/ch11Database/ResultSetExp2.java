/*
stmt.execute(queryStringWithUnknownResults);
while (true) {
int rowCount = stmt.getUpdateCount();
if (rowCount > 0) { // 它是更新计数
System.out.println("Rows changed = " + count);
stmt.getMoreResults();
continue;
}
if (rowCount == 0) { // DDL 命令或  0 个更新
System.out.println(" 
No rows changed or statement was DDL command");
stmt.getMoreResults();
continue;
}

// 执行到这里，证明有一个结果集
// 或没有其它结果
ResultSet rs = stmt.getResultSet;
if (rs != null) {
…// 使用元数据获得关于结果集列的信息
}
…
}
*/
