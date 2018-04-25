import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
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

    boolean doesOverlap(Interval a, Interval b)
    {
        return (Math.min(a.end, b.end) >= Math.max(a.start, b.start));
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        int n = intervals.size();

        if(n==0){
            res.add(newInterval);
            return res;
        }

        // Insert at corners
        if(newInterval.end < intervals.get(0).start ||
                newInterval.start > intervals.get(n-1).end){
            if(newInterval.end < intervals.get(0).start){
                res.add(newInterval);
            }

            res.addAll(intervals);

            if(newInterval.start > intervals.get(n-1).end){
                res.add(newInterval);
            }
            return res;
        }

        if(newInterval.start <= intervals.get(0).start &&
                newInterval.end >= intervals.get(n-1).end){
            res.add(newInterval);
            return res;
        }

        boolean overlap = true;
        for(int i=0; i<n; i++){
            overlap = doesOverlap(intervals.get(i), newInterval);
            if(!overlap){
                res.add(intervals.get(i));

                if(i < n && newInterval.start > intervals.get(i).end &&
                        i+1 < n && newInterval.end < intervals.get(i+1).start){
                    res.add(newInterval);
//                    res.addAll(intervals.subList(i+1, n));
//                    return res;
                }
                continue;
            }

            Interval temp = new Interval(0,0);
            //Starting time of new merged interval is
            // minimum of starting time of both
            // overlapping intervals.
            temp.start = Math.min(intervals.get(i).start, newInterval.start);

            // Traverse until intervals are overlapping
            while(i<n && overlap){
                temp.end = Math.max(intervals.get(i).end, newInterval.end);

                if(i == n-1)
                    overlap = false;
                else
                    overlap = doesOverlap(intervals.get(i+1), newInterval);

                i++;
            }
            i--;
            res.add(temp);
        }

        return res;
    }

    public static void main(String args[]){
        List<Interval> ip = new ArrayList<>();
        Interval newInterval1 = new Interval();

        newInterval1.start = 1;
        newInterval1.end = 5;
        ip.add(newInterval1);

        Interval newInterval2 = new Interval();
        newInterval2.start = 3;
        newInterval2.end = 5;
        ip.add(newInterval2);

        Interval newInterval3 = new Interval();
        newInterval3.start = 6;
        newInterval3.end = 7;
        ip.add(newInterval3);

        Interval newInterval4 = new Interval();
        newInterval4.start = 8;
        newInterval4.end = 10;
        ip.add(newInterval4);

        Interval newInterval5 = new Interval();
        newInterval5.start = 12;
        newInterval5.end = 16;
        ip.add(newInterval5);

        Interval newInterval = new Interval();
        newInterval.start = 6;
        newInterval.end = 8;

        List<Interval> res = new ArrayList<>();
        InsertInterval obj = new InsertInterval();
        res = obj.insert(ip, newInterval);

        for (Interval i: res){
            System.out.println(i);
        }
    }
}
