package fr.uge.bank;

import java.util.HashMap;

public class AccountManager {
    
    private HashMap<Integer, Float> accounts = new HashMap<>();
    
    
    public AccountManager() {
        accounts.put(1, 1_000.f);
        accounts.put(2, 10.f);
        accounts.put(3, 2_663_884.f);
        accounts.put(4, 10_000.f);
        accounts.put(99, 1000.f); // for if-service demonstration
    }

    public boolean withdraw(float amount, int id) {
        Float balance = accounts.get(id);
        if (balance-amount < 0 || balance == null)
            return false;
        balance -= amount;
    	accounts.put(id, balance);
        return true;
        
    }
    
    public void deposit(float amount, int id) {
    	Float oldAmount = accounts.get(id);
    	oldAmount += amount;
    	accounts.put(id, oldAmount);
    }
    
    public float amount(int id) {
        return accounts.get(id);
    }
}
