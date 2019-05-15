public class Main
{
    public static void main(String[] args)
    {
        ReadersWritersProblem problem;
        if (0 == args.length) {
            help();
            return;
        }
        switch (args[0]) {
            case "semaphores":
                problem = new ReadersWritersProblemSemaphores();
                break;
            case "conditions":
                problem = new ReadersWritersProblemConditions();
                break;
            default:
                help();
                return;
        }
        int readersAll = 1 < args.length ? Integer.parseInt(args[1]) : 10;
        int writersAll = 2 < args.length ? Integer.parseInt(args[2]) : 1;
        int readerRepeat = 3 < args.length ? Integer.parseInt(args[3]) : 10;
        int writerRepeat = 4 < args.length ? Integer.parseInt(args[4]) : 10;
        int readerSleep = 5 < args.length ? Integer.parseInt(args[5]) : 100;
        int writerSleep = 6 < args.length ? Integer.parseInt(args[6]) : 200;

        long start = System.currentTimeMillis();
        problem.solve(
            readersAll,
            writersAll,
            readerRepeat,
            writerRepeat,
            readerSleep,
            writerSleep
        );
        long stop = System.currentTimeMillis();

        System.out.printf("%d\t%d\t%d\n",
            writersAll, readersAll, stop - start);
    }

    static void help()
    {
        System.err.println("Usage java Main" +
            " {semaphores | conditions}" +
            " [readers_all" +
            " [writers_all" +
            " [reader_repeat" +
            " [writer_repeat" +
            " [reader_sleep" +
            " [writer_sleep" +
            "]]]]]]");
    }
}
