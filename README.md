# SE2_PROJECT_GROUP11
Preview UI Design:
https://xd.adobe.com/view/3760ffff-db7e-4347-4e6d-b7d6e97163be-4151/


# SET UP TO RUN PROJECT
In our project we mainly use tools: 
- InteliJ (editor)
- Mamp Pro (server)


# Step to run

- Use Mamp or Xampp to run server. (user/pass you need to know inorder to change in connection '\src\connect\Connect.java' beware of port number, password)
- If you user InteliJ. Open file with InteliJ (assum that Tomcat is already setup in your IDE). Add library by go to 'Project Structure'--> Library. Press "+" at the left conner. Local file lib at 'Covid19\web\WEB-INF\lib' and add all lib. 
- There is a file name "daily-cases-covid-19.csv" locate at "src". You need to coppy absolute path of this file and change the line at file "ChartDao.java" at 'Covid19\src\net\se2project\covidtracker\dao' line 46. 
- Run Tomcat along with project. GOOD TO GO. 


# Important!
- In test part. The statistic is changing overtime. So we need to update the instant of object when we run test again.
- Statistic of Vietnam and World is contain in ".csv" file. If we want to update, update in this file then update again in web interface. Or we can find that ".csv" file on the internet.
