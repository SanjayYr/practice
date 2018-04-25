import java.util.ArrayList;
import java.util.List;

public class KthPermutation {

    public static String getPermutation(int n, int k) {
        k-=1;
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<n; i++){
            list.add(i+1);
        }
        StringBuilder res = new StringBuilder();
        for(int i=n ; i>0; i--){
            if(k==0){
                for (Integer val:list)
                    res.append(val.toString());
                break;
            }
            System.out.println("k: " + k + ", i: " + i);
            int v = k/factorial(i-1);
            k= k-(v*factorial(i-1));
            System.out.println("V: " + v);
            res.append(list.get(v).toString());
            System.out.println("List val: "+list.get(v));
            list.remove(v);
        }
        return res.toString();
    }

    public static int factorial(int n){
        if(n==0 || n==1)
            return 1;
        return n* factorial(n-1);
    }

    public static void main(String args[]){
        String res = getPermutation(3, 6);

        System.out.println(res);

        System.out.println("  Bfsdf wer  ".trim());

    }
}
