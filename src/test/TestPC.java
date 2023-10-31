package test;

class Producer extends Thread
{
	Queue q;
	Producer(Queue q)
	{
		this.q=q;
	}
	@Override
	public void run()
	{
		int i=1;
		while(true)
		{
			q.put(i++);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
}
class Consumer extends Thread
{
	Queue q;
	Consumer(Queue q)
	{
		this.q=q;
	}
	@Override
	public void run()
	{
		while(true)
		{
			q.get();
		}
	}
}
class Queue
{
	int i;
	boolean flag=false;
	synchronized public void put(int x)
	{
		try 
		{
			if(flag==true)
			{
				wait();
			}
			else
			{
			i=x;
			System.out.println("I have produce the data: "+x);
			flag=true;
			notify();
			}
		}
		catch(Exception e)
		{
			System.out.println("something wrong..");
		}
	}
	synchronized public void get()
	{
		try 
		{
	
			if(flag==false)
			{
				wait();
			}
			else
			{
				System.out.println("I have consume the data: "+i);
				flag=false;
				notify();
			}
		}
		catch(Exception e)
		{
			System.out.println("Something wrong..");
		}
	}
		
		
}
public class TestPC {

	public static void main(String[] args) 
	{
		Queue q=new Queue();
		
		Producer p=new Producer(q);
		Consumer c=new Consumer(q);
		
		p.start();
		c.start();

	}

}
