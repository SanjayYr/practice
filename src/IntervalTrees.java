import java.util.ArrayList;
import java.util.List;

public class IntervalTrees {

    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static class IntervalNode {
        Interval in;
        int max;
        IntervalNode left, right;

        public IntervalNode(Interval i){
            this.in = new Interval(i.start, i.end);
            this.max = i.end;
            this.left = this.right = null;
        }

        @Override
        public String toString() {
            return "IntervalNode{" +
                    "in=" + in +
                    ", max=" + max +
                    '}';
        }
    }

    public static IntervalNode insert(IntervalNode root, Interval in){

        if(root == null)
            return new IntervalNode(in);

        int s = root.in.start;

        if(in.start < s)
            root.left = insert(root.left, in);
        else
            root.right = insert(root.right, in);

        if(root.max < in.end)
            root.max = in.end;

        return root;
    }

    public static boolean doOverlap(Interval a, Interval b)
    {
        return (Math.min(a.end, b.end) >= Math.max(a.start, b.start));
    }

    public static Interval overlapSearch(IntervalNode root, Interval in){
        if(root == null) return null;

        if(doOverlap(root.in, in))
            return root.in;

        if(root.left != null && root.left.max >= in.start)
            return overlapSearch(root.left, in);

        return overlapSearch(root.right, in);
    }

    public static void inOrder(IntervalNode root){
        if(root == null)
            return;

        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
    }

    public static void main(String args[]){
        List<Interval> ip = new ArrayList<>();
        ip.add(new Interval(15,20));
        ip.add(new Interval(10,30));
        ip.add(new Interval(17,19));
        ip.add(new Interval(5,20));
        ip.add(new Interval(12,15));
        ip.add(new Interval(30,40));

        IntervalNode root = null;
        for (Interval i:ip){
            root = insert(root, i);
        }

        System.out.println("Inorder of interval tree");
        inOrder(root);

        Interval x = new Interval(6,7);

        Interval res = overlapSearch(root,x);

        if (res == null)
            System.out.println("No overlapping interval");
        else
            System.out.println("Overlaps with " + res);

    }
}
