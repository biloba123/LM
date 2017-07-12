package com.lvqingyang.librarymanager.bean;

/**
 * 　　┏┓　　  ┏┓+ +
 * 　┏┛┻━ ━ ━┛┻┓ + +
 * 　┃　　　             ┃
 * 　┃　　　━　　   ┃ ++ + + +
 * ████━████     ┃+
 * 　┃　　　　　　  ┃ +
 * 　┃　　　┻　　  ┃
 * 　┃　　　　　　  ┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽保佑
 * 　　　┃　　　┃    代码无bug！
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * ━━━━━━神兽出没━━━━━━
 * Author：LvQingYang
 * Date：2017/7/8
 * Email：biloba12345@gamil.com
 * Info：
 */

public class Statistic {

    /**
     * canBorrowCount : 418
     * borrowedCount : 2
     * todayBorrowCount : 0
     * todayReserveCount : 0
     * userTotalCount : 5
     * todayRegisterCount : 0
     */

    private int canBorrowCount;
    private int borrowedCount;
    private int todayBorrowCount;
    private int todayReserveCount;
    private int userTotalCount;
    private int todayRegisterCount;

    public void setCanBorrowCount(int canBorrowCount) {
        this.canBorrowCount = canBorrowCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
    }

    public void setTodayBorrowCount(int todayBorrowCount) {
        this.todayBorrowCount = todayBorrowCount;
    }

    public void setTodayReserveCount(int todayReserveCount) {
        this.todayReserveCount = todayReserveCount;
    }

    public void setUserTotalCount(int userTotalCount) {
        this.userTotalCount = userTotalCount;
    }

    public void setTodayRegisterCount(int todayRegisterCount) {
        this.todayRegisterCount = todayRegisterCount;
    }

    public int getCanBorrowCount() {
        return canBorrowCount;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public int getTodayBorrowCount() {
        return todayBorrowCount;
    }

    public int getTodayReserveCount() {
        return todayReserveCount;
    }

    public int getUserTotalCount() {
        return userTotalCount;
    }

    public int getTodayRegisterCount() {
        return todayRegisterCount;
    }
}
