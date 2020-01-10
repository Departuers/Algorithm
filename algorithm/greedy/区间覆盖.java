package greedy;

public class 区间覆盖 {
    static class Node {
        public int start;
        public int end = -1;

        public Node(int start) {
            this.start = start;
        }

        public int getStart() {
            return start;
        }

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        public int hashCode() {
            return start * 100 + end * 10;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return start == node.start &&
                    end == node.end;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) {

    }

    public static void F() {

    }
}
