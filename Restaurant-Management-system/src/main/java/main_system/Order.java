package main_system;


import com.sun.tools.javac.comp.Check;
import constants_enums.OrderStatus;
import model.Chef;

import java.util.Date;

public class Order {
  private int OrderID;
  private OrderStatus status;
  private Date creationTime;

  private Meal[] meals;
  private Table table;
  private Check check;
  private Waiter waiter;
  private Chef chef;

  public boolean addMeal(Meal meal);
  public boolean removeMeal(Meal meal);
  public OrderStatus getStatus();
  public boolean setStatus(OrderStatus status);
}