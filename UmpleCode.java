/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5105.3f8cbd5a3 modeling language!*/



// line 2 "model.ump"
// line 51 "model.ump"
public class TicTacToe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TicTacToe State Machines
  public enum Sm { xTurn, oTurn, oWin, xWin, draw }
  private Sm sm;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TicTacToe()
  {
    setSm(Sm.xTurn);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getSmFullName()
  {
    String answer = sm.toString();
    return answer;
  }

  public Sm getSm()
  {
    return sm;
  }

  public boolean play()
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case xTurn:
        setSm(Sm.oTurn);
        wasEventProcessed = true;
        break;
      case oTurn:
        setSm(Sm.xTurn);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean reset()
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case xTurn:
        setSm(Sm.xTurn);
        wasEventProcessed = true;
        break;
      case oTurn:
        setSm(Sm.xTurn);
        wasEventProcessed = true;
        break;
      case oWin:
        setSm(Sm.xTurn);
        wasEventProcessed = true;
        break;
      case xWin:
        setSm(Sm.xTurn);
        wasEventProcessed = true;
        break;
      case draw:
        setSm(Sm.xTurn);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean win()
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case xTurn:
        setSm(Sm.xWin);
        wasEventProcessed = true;
        break;
      case oTurn:
        setSm(Sm.oWin);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean gridFull()
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case xTurn:
        setSm(Sm.draw);
        wasEventProcessed = true;
        break;
      case oTurn:
        setSm(Sm.draw);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean nextRound()
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case oWin:
        setSm(Sm.xTurn);
        wasEventProcessed = true;
        break;
      case xWin:
        setSm(Sm.xTurn);
        wasEventProcessed = true;
        break;
      case draw:
        setSm(Sm.xTurn);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setSm(Sm aSm)
  {
    sm = aSm;
  }

  public void delete()
  {}

}