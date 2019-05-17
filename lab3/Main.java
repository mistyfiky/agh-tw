import java.util.*;

class Main
{
    public static void main(String[] args)
    {
        BoundedBufferProblem problem;
        int producersAll;
        int consumersAll;
        int capacity;
        List<Integer> producersDelays;
        List<Integer> consumersDelays;
        LinkedList<String> list = new LinkedList<>(Arrays.asList(args));
        try {
            String type = list.removeFirst();
            switch (type) {
                case "conditions":
                    problem = new BoundedBufferProblemConditions();
                    break;
                case "semaphores":
                    problem = new BoundedBufferProblemSemaphores();
                    break;
                default:
                    throw new NoSuchElementException();
            }
            producersAll = Integer.parseInt(list.removeFirst());
            consumersAll = Integer.parseInt(list.removeFirst());
            capacity = Integer.parseInt(list.removeFirst());
            producersDelays = new ArrayList<>();
            for (int i = 0; i < producersAll; i++) {
                producersDelays.add(i, Integer.parseInt(list.removeFirst()));
            }
            consumersDelays = new ArrayList<>();
            for (int i = 0; i < consumersAll; i++) {
                consumersDelays.add(i, Integer.parseInt(list.removeFirst()));
            }
        } catch (NoSuchElementException e) {
            help();
            return;
        }

        problem.solve(
            producersAll,
            consumersAll,
            capacity,
            producersDelays,
            consumersDelays
        );
    }

    static void help()
    {
        System.err.println("usage: java Main" +
            " {conditions | semaphores}" +
            " <producers_all>" +
            " <consumers_all>" +
            " <capacity>" +
            " <producers_delays ...>" +
            " <consumers_delays ...>");
    }
}

