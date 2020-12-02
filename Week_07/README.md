插入100万条数据测试，
1.使用statment语句一条一条插入10万条数据，用时2762s（ps：开十个线程然后一条条插入时间太长，故只插入10万条数据）
 ![image](https://github.com/ipocater/JAVA-000/blob/main/Week_07/DBTestImg/%E4%B8%80%E6%9D%A1%E6%8F%92%E5%85%A510w.png)
2.使用preparedStatement语句批量插入10万条数据，用时61s（ps:开十个线程，循环十次，每次插入1000条数据）
  ![image](https://github.com/ipocater/JAVA-000/blob/main/Week_07/DBTestImg/%E6%89%B9%E9%87%8F10w.png)
3.使用preparedStatement语句批量插入100万条数据，用时607s（ps:单线程循环100次，每次插入10000条数据）
  ![image](https://github.com/ipocater/JAVA-000/blob/main/Week_07/DBTestImg/100%E4%B8%87%E6%9D%A1100%E6%AC%A11%E4%B8%87%E6%9D%A1.png)
4.使用preparedStatement语句批量插入100万条数据，用时611s（ps:开10个线程，每个线程循环10次，每次插入10000条数据）
  ![image](https://github.com/ipocater/JAVA-000/blob/main/Week_07/DBTestImg/100%E4%B8%87%E6%9D%A1%E5%BC%80%E5%8D%81%E4%B8%AA%E7%BA%BF%E7%A8%8B.png)

总结：使用preparedStatement预编译后，插入的性能达到了两个数量级的提升。但是再达到1w/6s的性能后，很难再有过大的提升。由于本人使用的是阿里云上面的数据库，可能很容易达到性能瓶颈。

