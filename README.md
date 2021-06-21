# youtube-player
1. you can search any youtube video and play it
2. you can set equalizer video
3. you can share video to your team group in local server
4. you can save video and listen next time
5. auto play option
6. when this app is open it list all video from my playlist : https://www.youtube.com/watch?v=luSHpiqa-f0&list=PLyt2v1LVXYS1jKphKNZFw0gF-wxOvKXVi
7. this app using VLCJ library ( no need install it has include in project )
## Config API
- if you want to use this app you need youtube API key
- see this for how to get Youtube API key : https://youtu.be/Ovcmjo0D-yw
- and replace your key to project :
    . com.raven.youtube.service.YoutubeService
    . look at line 44 in class YoutubeService ( String apiKey = "your api key here"; ) replace it
    . done
## how to use shared option
### for server
1. pc server must close windows firewall or open port 9999
2. click on shared tab
3. click on Create Own Server
4. input your server name and click OK ( Ex: Group 001 )
5. you will see server is starting ( done )
### for client
1. click on shared tab
2. click on Connect to another Server
3. input IP address pc server and click Connect
4. inter your name and click OK
### how to shared
1. click on menu ... in video box at the top right and click share to server
2. client and server will see your video has shared
### screenshot
![2021-06-21_203145](https://user-images.githubusercontent.com/58245926/122770586-cc256c80-d2cf-11eb-90bc-9a73f30f44f7.png)
