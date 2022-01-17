# LibraryManager
Main project: library management system
spring jpa, spring security, REST API, PostgreSQL, lombok

This API allows you to manage your library

Users detals table:

![2022-01-08_18h21_46](https://user-images.githubusercontent.com/63502583/148654484-7e7330e4-d4f4-4de2-a5d3-fc95a0d51b09.png)

Borrwed books table: 

![2022-01-08_18h22_02](https://user-images.githubusercontent.com/63502583/148654526-c5228035-d274-42dd-b0c8-fb67950cc5f4.png)

books table:

![2022-01-08_18h21_55](https://user-images.githubusercontent.com/63502583/148654516-8837f9fd-68c4-4398-9ab3-96b432b7325e.png)


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
GET

![image](https://user-images.githubusercontent.com/63502583/148653652-b3151941-b805-4eaa-bef0-a30bbf87f75d.png)

POST admin is able to add new book

![image](https://user-images.githubusercontent.com/63502583/148695280-abeb74dc-b82f-4df2-be1e-76a2b2d787f9.png)

#/admin/users

![image](https://user-images.githubusercontent.com/63502583/148653681-2b20e50b-6f76-4704-a5dc-e60a80c3915a.png)

#/admin/delete/{title}

![image](https://user-images.githubusercontent.com/63502583/148654160-cfb566ad-5f15-49e2-93c5-ad0e5c520c39.png)

custom exception while admin trying to delete book that is borrowed

![image](https://user-images.githubusercontent.com/63502583/148654243-da715677-6914-4600-a0a1-ca679c9c608a.png)

#/admin/timeout

admin is able to see users emails that dont return book before toReturn date

![2022-01-09_19h18_10](https://user-images.githubusercontent.com/63502583/148695211-1b49bee1-2781-4ad6-bdf9-d9c1ed46a2c8.png)
d
