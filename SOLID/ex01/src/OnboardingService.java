import java.util.*;

public class OnboardingService {

    private final StudentRepository db;
    private final StudentParser parser;
    private final StudentValidator validator;
    private final ConsolePrinter printer;

    public OnboardingService(StudentRepository db) {
        this.db = db;
        this.parser = new StudentParser();
        this.validator = new StudentValidator();
        this.printer = new ConsolePrinter();
    }

    public void registerFromRawInput(String raw) {

        // 1) print input
        printer.printInput(raw);

        // 2) parse
        StudentInput input = parser.parse(raw);

        // 3) validate
        List<String> errors = validator.validate(input);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        // 4) create student record
        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(
                id,
                input.name,
                input.email,
                input.phone,
                input.program
        );

        // 5) save
        db.save(rec);

        // 6) print confirmation
        printer.printSuccess(id, db.count(), rec);
    }
}
