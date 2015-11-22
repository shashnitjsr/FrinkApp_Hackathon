package com.frink.hackathon.coupanlist;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by shashwatsinha on 16/10/15.
 */
public class CardListModel implements Serializable {

    ArrayList<CardModel> coupons;

    public ArrayList<CardModel> getCoupans() {
        return coupons;
    }


    public static class CardModel {
        String title;
        String expiry;
        String term_cond;
        String coupon_company;
        private String name_person;
        private String person_image;
        

        public CardModel(String title, String expiry, String term_cond, String coupan_company, String name_person, String person_image) {
            this.title = title;
            this.expiry = expiry;
            this.term_cond = term_cond;
            this.coupon_company = coupan_company;
            this.name_person = name_person;
            this.person_image = person_image;
        }

        @Override
        public String toString() {
            String string = title + expiry + term_cond + coupon_company;

            return string;
        }

        String getTitle() {
            return title;
        }

        String getExpiry() {
            return expiry;
        }

        String getTerm_cond() {
            return term_cond;
        }

        String getCoupon_company() {
            return coupon_company;
        }

        public String getNamePerson() {
            return name_person;
        }

        public String getPersonImage() {
            return person_image;
        }
    }
}
