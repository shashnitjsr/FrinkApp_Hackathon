package com.frink.hackathon.addcardlist;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by shashwatsinha on 17/10/15.
 */
public class BackCardTypeModel implements Serializable {

    ArrayList<BankCards> chacha;

    ArrayList<BankCards> getChacha() {
        return chacha;
    }

    public static class BankCards {
        String name;
        ArrayList<Card> value;

        String getName() {
            return name;
        }

        ArrayList<String> bankCards() {
            ArrayList<String> list = new ArrayList<String>();
            for (Card name : value) {
                list.add(name.getCardName());
            }
            return list;
        }

        ArrayList<Integer> bankCardIds() {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (Card name : value) {
                list.add(name.getId());
            }
            return list;
        }
    }

    public static class Card {
        public String name;
        public int id;

        public String getCardName() {
            return name;
        }

        public int getId() {
            return id;
        }


    }
}
