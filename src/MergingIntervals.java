import java.util.*;

class SortbyStart implements Comparator<MergingIntervals.Interval>
{
    // Used for sorting in descending order of start values
    public int compare(MergingIntervals.Interval a, MergingIntervals.Interval b)
    {
        return b.start - a.start;
    }
}

public class MergingIntervals {
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



    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();

        Collections.sort(intervals, new SortbyStart());

        int index = 0; // Stores index of last element
        // in output array (modified arr[])

        // Traverse all input Intervals
        for (int i=0; i<intervals.size(); i++)
        {
            // If this is not first Interval and overlaps
            // with the previous one
            if (index != 0 && intervals.get(index-1).start <= intervals.get(i).end)
            {
                while (index != 0 && intervals.get(index-1).start <= intervals.get(i).end)
                {
                    // Merge previous and current Intervals
                    intervals.get(index-1).end = Math.max(intervals.get(index-1).end, intervals.get(i).end);
                    intervals.get(index-1).start = Math.min(intervals.get(index-1).start, intervals.get(i).start);
                    index--;
                }
            }
            else // Doesn't overlap with previous, add to
                // solution
                intervals.set(index, intervals.get(i));

            index++;
        }

        System.out.println(index);

        return intervals.subList(0, index);
    }

    public static void main(String args[]){

        Interval arr[] = new Interval[4];

        arr[0] = new Interval(1,3);
        arr[1] = new Interval(2,6);
        arr[2] = new Interval(8,10);
        arr[3] = new Interval(15,18);


        List<Interval> res = new ArrayList<>(Arrays.asList(arr));

        res = merge(res);

        for(Interval i: res){
            System.out.println(i);
        }
    }
}
