# Restaurant Point Operating System

This is a program I wrote when I was learning Java and the Java Swing Library in 2018. I was working as a restaurant server at the time and decided to learn Java to create a quick UI similar to that of the restaurant I was working in.

### Login
Here is a simple Login Menu the user is prompted with at the beginning. If the user's ID number matches an employee ID number in the Employee file the employee's home screen is displayed.

Login View            |  Login User
:-------------------------:|:-------------------------:
![](https://github.com/eduardor626/RestaurantPOS/blob/master/images/LoginMenu.PNG)  |  ![](https://github.com/eduardor626/RestaurantPOS/blob/master/images/LoginMenu2.PNG)

### Employee Home Menu
When the employee's ID is verified their home screen window is displayed. They are presented with a variety of buttons to select. When pressing New Table they are prompted to enter their `New Table` number. The employee inputs their table number and the New Table's button will now appear on their home screen window.

User Home           |  When User Selects New Table
:-------------------------:|:-------------------------:
![](https://github.com/eduardor626/RestaurantPOS/blob/master/images/UserHome.PNG)  |  ![](https://github.com/eduardor626/RestaurantPOS/blob/master/images/UserHomeTable.PNG)

Adding Two Tables          |  Adding Multiple Tables
:-------------------------:|:-------------------------:
![](https://github.com/eduardor626/RestaurantPOS/blob/master/images/UserHomeTable3.PNG)  | ![](https://github.com/eduardor626/RestaurantPOS/blob/master/images/UserHomeTable4.PNG)

### Taking the Order of a Table

Now the employee has the option of taking the table's order by pressing the certain table number's button. Once the employee does this the Order Menu window is displayed. The Order Menu lists the variety of food that is available in the restaurant. (I served at an Asian American restaurant, hence the food names). The user then goes through pressing which category to choose from. `Beverages, Soups & Salads, Market Sides, Chicken, Beef & Pork, Vegeterian, etc`. 

Order Menu        |  Selecting Items from Menu
:-------------------------:|:-------------------------:
![](https://github.com/eduardor626/RestaurantPOS/blob/master/images/Menu.PNG)  | ![](https://github.com/eduardor626/RestaurantPOS/blob/master/images/Menu2.PNG)

The items are highlighted because this shows that they are still not being sent to the kitchen screen. This ensures that the employee knows what is being sent before actually pressing the `Order` button. When the `Order` button is pressed, the employee will now see that the items are finalized and have now been sent to the kitchen. The employee can now press `Done` to return to their home screen window with all their tables and exit. The user also has the option of selecting `Close Table` which results in a Payment Window where the server can close the table's tab. 

### Accepting Payment and Closing the Table

Payment Screen       |  Close Table
:-------------------------:|:-------------------------:
![](https://github.com/eduardor626/RestaurantPOS/blob/master/images/Payment.PNG)  | ![](https://github.com/eduardor626/RestaurantPOS/blob/master/images/Payment2.PNG)

The employee collects the form of payment and and inputs the amount given. If the amount exceeds the total it will let the employee know how much money is owed to the customer. Once the window recognizes that the amount owed has been paid, the `Close` button will appear. This button allows the user to close out the table's tab and delete it from their home screen. 



## Key Take Aways:
```
This was a very fun program to do. The code is not at all the prettiest but getting things to 
look 'nice' definitely made it an interesting mini project. There is many buttons that are 
not implemented such as the Tips, Sales, Break, Clock Out buttons on the home screen. The Payment 
Menu was my "get it done" part of the project and I could've included icons of the corresponding 
dollar bills on each button. Overall, it was fun learning and putting together something quick in 
Java that really introduced me to the GUI side of programming. It could've been so much cleaner 
if I had used certain data structures or proper proportions of Panels in Swing. But overall, fun 
project and definitely good to see how much I've grown over the years. Cheers!
```
