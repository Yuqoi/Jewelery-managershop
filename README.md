
<h1>Watch store Manager</h1>


<h4 align="center">A javafx application made for managing a "watch store" using maven tool and mysql database</h4> <br/>

1. login panel
   * login -> `admin`
   * password -> `pass` 
2. After correctly loggin in we are located in main panel that has 3 other panels<br/>
   2.1 Inventory panel, it's used for getting all watches from the database, we have few option here
      - Button to add new Watch to the database (has all the error event handlers)
      - Button to remove selected watch from the table
      - Button for refreshing data in table
      - Button for updating current selected Watch
      - Button for making an Invoice. It creates a pdf file (from themeplate) and sets all the given data into it. Created **Invoice** will be located in `src/main/resources/org/yuqoi/managerapp/invoices/`. After that it sends the information into database
      
   2.2 Sales panel, shows data from `invoice` table in our database.
      
   2.3 Settings panel, only serves purpose for changing password in our database, gets the name from which we used in login panel and shows it in this panel section, the password is hashed by SHA-256 algorithm


#
<h3 align="center">Images</h3>
<div align="center">
   <img src="https://github.com/Yuqoi/WatchStore_Manager/assets/73469531/f6f8e98f-2446-43f2-aea1-5c75f758d087" width="500" height="300" />
   <img src="https://github.com/Yuqoi/WatchStore_Manager/assets/73469531/10b0458f-08d9-4fab-8b28-e071d3841b45" width="500" height="300" />
   <img src="https://github.com/Yuqoi/WatchStore_Manager/assets/73469531/4330fd24-e407-4fb8-87d8-2a08c1b77bc8" width="500" height="300" />
</div>

#

<p align="center" style="font-size:20px;">
   🚀 All work has been done by me without using someone else's code <br/>
     © 2024 WatchStore_manager. All rights reserved.
</p>
