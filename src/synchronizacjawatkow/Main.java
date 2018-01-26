
package synchronizacjawatkow;

    public class Main {

        public static void main(String[] args) 
        {
//           Object lock = new Object();
//
//           Thread watek1 = new Thread (new WypisanieRunnable(lock), "Wątek 1");
//           Thread watek2 = new Thread (new WypisanieRunnable(lock), "Wątek 2");
//
//           watek1.start();
//           watek2.start();
            
            Counter licznik = new Counter();
            
            Thread watek3 = new Thread(new CounterRunnable(licznik,false),"Maleje");
            Thread watek4 = new Thread(new CounterRunnable(licznik,true),"Rośnie");
            watek3.start();
            watek4.start();

        }

    }

        class WypisanieRunnable implements Runnable
        {
            static String msg[] = {"To", "jest", "synchronicznie", "wypisana", "wiadomość!"};
            private Object lock;


            public WypisanieRunnable(Object lock)
            {
                this.lock = lock;
            }

            @Override
            public void run() {
                display(Thread.currentThread().getName());
            }

            public void display(String threadName)
            {
                synchronized(lock)
                {
                for (int i =0; i < msg.length; i++)
                {
                    System.out.println(threadName + ": " + msg[i]);

                    try 
                    {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
                }
            }

        }
            
        class Counter
        {

            private int counter = 50;
            
            public void increaseCounter()
            {
                counter++;
                System.out.println(Thread.currentThread().getName() + ": " + counter);
            }
            
            public void decreaseCounter()
            {
                counter--;
                System.out.println(Thread.currentThread().getName() + ": " + counter);                
            }
            }
        

        class CounterRunnable implements Runnable
        {
            boolean increase;
            Counter licznik;
            
            public CounterRunnable (Counter licznik, boolean increase)
            {
                this.licznik = licznik;
                this.increase = increase;
            }
            
            @Override
            public void run() 
            {
               synchronized(licznik)
               {
               for (int i = 0; i < 50; i++)
               {
                   if (increase)
                       licznik.increaseCounter();
                   else
                       licznik.decreaseCounter();
                   try
                   {
                       Thread.sleep(100);
                   }
                   catch (InterruptedException ex)
                   {
                       System.out.println(ex.getMessage());
                   }
               }
               }
            }
            
        }

