# LibraryManager
Main project: library management system

This API allows you to manage your library

*User*

#Registration: 

![image](https://user-images.githubusercontent.com/63502583/148652019-5e51cba5-c364-4973-83e1-3e890e82edfb.png)

#Regex:

![image](https://user-images.githubusercontent.com/63502583/148652008-5e52e29a-5bb3-4f85-9d51-c5b78c411125.png)

#Login:

![image](https://user-images.githubusercontent.com/63502583/148652398-fd9c4a18-090c-437f-8151-0fd913505e1a.png)

#/books

![image](https://user-images.githubusercontent.com/63502583/148652380-f2057fa4-98bc-453e-ae21-f1c69dad13d2.png)
  
#/books/borrow

![image](https://user-images.githubusercontent.com/63502583/148652473-2568fe8d-6003-48fc-ac73-4a8509cf3491.png)

#/books/borrow/{title}

![image](https://user-images.githubusercontent.com/63502583/148652517-a6962e29-ec12-45c8-b212-cd17ace6a485.png)

out of sotck (booksAvaible==0)

![image](https://user-images.githubusercontent.com/63502583/148652561-cf4aa1b0-f011-434f-b082-9764592f1213.png)

#books/return/{title}

if user borrow the same book couple times apk will delete the oldest borrowed book

![image](https://user-images.githubusercontent.com/63502583/148652997-71782220-0917-410c-8fdf-e3f177dfbb8f.png)

![image](https://user-images.githubusercontent.com/63502583/148653055-538a1316-fc38-4af7-949f-f3d17d17f3f0.png)

custom exception if user try to return book that dont rented

![image](https://user-images.githubusercontent.com/63502583/148653500-19281859-f9a1-41be-a7e9-75671313ccdb.png)

*Admin*

#/admin

![image](https://user-images.githubusercontent.com/63502583/148653652-b3151941-b805-4eaa-bef0-a30bbf87f75d.png)

#/admin/users

![image](https://user-images.githubusercontent.com/63502583/148653681-2b20e50b-6f76-4704-a5dc-e60a80c3915a.png)

#/admin/delete/{title}

![image](https://user-images.githubusercontent.com/63502583/148654160-cfb566ad-5f15-49e2-93c5-ad0e5c520c39.png)

custom exception while admin trying to delete book that is borrowed

![image](https://user-images.githubusercontent.com/63502583/148654243-da715677-6914-4600-a0a1-ca679c9c608a.png)

#/admin/timeout

admin is able to see user email that dont return book before toReturn date

![image](https://user-images.githubusercontent.com/63502583/148654271-83eb9513-ccd8-4667-bd10-e8c491a2c4d5.png)
