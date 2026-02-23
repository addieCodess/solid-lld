interface StudentRepository {
    void save(StudentRecord rec);
    int count();
    List<StudentRecord> getAll();
}
