/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mariq.rasyid.latihanmf6.Model;

import java.util.Date;

/**
 *
 * @author Blizzard
 */
public class Costumer {

    private String Name, Hari;
    private int Book_Type;
    private Date Pinjam, Kembali;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getBook_Type() {
        return Book_Type;
    }

    public void setBook_Type(int Book_Type) {
        this.Book_Type = Book_Type;
    }

    public Date getPinjam() {
        return Pinjam;
    }

    public void setPinjam(Date Pinjam) {
        this.Pinjam = Pinjam;
    }

    public Date getKembali() {
        return Kembali;
    }

    public void setKembali(Date Kembali) {
        this.Kembali = Kembali;
    }

    public String getHari() {
        return Hari;
    }

    public void setHari(String Hari) {
        this.Hari = Hari;
    }

}
