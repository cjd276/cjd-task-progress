package com.cjd.rescue.product.service;

public class Test {

    public static void main(String[] args) {
        //同一个cardno 并发

        Test test = new Test();

        Thread t1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    System.out.println(test.get("cardNo-"+1));
                }
            }
        });



        Thread t2 =  new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    System.out.println(test.get("cardNo-"+2));
                }
            }
        });
        Thread t3 =  new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    System.out.println(test.get("cardNo-"+3));
                }
            }
        });
        Thread t4 =  new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    System.out.println(test.get("cardNo-"+4));
                }
            }
        });
        Thread t10 =  new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    System.out.println(test.get("cardNo-"+10));
                }
            }
        });
        Thread t6 =  new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    System.out.println(test.get("cardNo-"+6));
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t10.start();
        t6.start();



    }



    public String get(String cardNo){
        Object c = cardNo;
        synchronized (c){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return cardNo;
        }
    }
}
