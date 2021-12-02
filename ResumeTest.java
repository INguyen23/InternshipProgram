import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResumeTest {
    private ArrayList<String> coursework = new ArrayList<String>();
    private ArrayList<String> awards = new ArrayList<String>();
    private ArrayList<String> scholarships = new ArrayList<String>();
    private ArrayList<Experience> experiences = new ArrayList<Experience>();
    private ArrayList<String> responsibilities = new ArrayList<String>();
    private Education education = new Education("UofSC", "Computer Science", "Math", 3.8, 2023, "Junior", coursework);
    private Resume resume = new Resume("Bob", "583-384-2039", "Columbia", education, awards, scholarships, experiences);

    @BeforeEach
    public void setup() {
        resume.getEducation().getCoursework().clear();
        resume.getAwards().clear();
        resume.getScholarships().clear();
        resume.getExperiences().clear();
    }

    @AfterEach
    public void tearDown() {
        resume.getEducation().getCoursework().clear();
        resume.getAwards().clear();
        resume.getScholarships().clear();
        resume.getExperiences().clear();
    }

    @Test
    public void editEducationCorrectTest() {
        ArrayList<String> cswk = new ArrayList<String>();
        cswk.add("Software Engineering");
        resume.editEducation("UofSC", "CS", "Math", 3.5, 2023, "Junior", cswk);
        Education edu = resume.getEducation();
        assertTrue(edu.getSchool().equals("UofSC") && edu.getMajor().equals("CS") && edu.getMinor().equals("Math") && edu.getGpa() == 3.5 && edu.getGraduationYear() == 2023 && edu.getClassStanding().equals("Junior") && edu.getCoursework().get(0).equals(cswk.get(0)));
    }

    @Test
    public void editEducationGpaNotIntTest() {
        ArrayList<String> cswk = new ArrayList<String>();
        cswk.add("Software Engineering");
        try {
            resume.editEducation("UofSC", "CS", "Math", 3, 2023, "Junior", cswk);
        }
        catch(IllegalArgumentException e) {
        }
        assertEquals(3, resume.getEducation().getGpa());
    }

    @Test
    public void editEducationGradYearNotIntTest() {
        ArrayList<String> cswk = new ArrayList<String>();
        cswk.add("Software Engineering");
        try {
            resume.editEducation("UofSC", "CS", "Math", 3, 2023, "Junior", cswk);
        }
        catch(IllegalArgumentException e) {
        }
        assertEquals(3, resume.getEducation().getGraduationYear());
    }

    @Test
    public void addScholarshipTest() {
        ArrayList<String> testArray = new ArrayList<String>();
        testArray.add("Palmetto");
        resume.addScholarship("Palmetto");
        assertEquals(testArray.get(0), resume.getScholarships().get(0));
    }

    @Test
    public void addAwardTest() {
        ArrayList<String> testArray = new ArrayList<String>();
        testArray.add("Medal");
        resume.addAward("Medal");
        assertEquals(testArray.get(0), resume.getAwards().get(0));
    }

    @Test
    public void addExperienceResponsabilityTest() {
        responsibilities.add("Coding");
        resume.addExperience("Software Intern", "Google", "Houston", "Jun 1", "July 2", responsibilities);
        assertEquals("Coding", resume.getExperiences().get(0).getResponsibilities().get(0));
    }

    @Test
    public void addExperienceTitleTest() {
        responsibilities.add("Coding");
        resume.addExperience("Software Intern", "Google", "Houston", "Jun 1", "July 2", responsibilities);
        assertEquals("Software Intern", resume.getExperiences().get(0).getTitle());
    }

    @Test
    public void removeExperienceTest() {
        responsibilities.add("Coding");
        resume.addExperience("Software Intern", "Google", "Houston", "Jun 1", "July 2", responsibilities);
        resume.removeExperience("Software Intern");
        ArrayList<Experience> exp = new ArrayList<Experience>();
        assertEquals(resume.getExperiences(), exp);
    }

    @Test   // Should remove only one experience if passes test
    public void removeExperienceDoubleTest() {
        responsibilities.add("Coding");
        resume.addExperience("Software Intern", "Google", "Houston", "Jun 1", "July 2", responsibilities);
        resume.addExperience("Software Intern", "Amazon", "NewYork", "May 9", "July 15", responsibilities);
        resume.removeExperience("Software Intern");
        ArrayList<Experience> exps = new ArrayList<Experience>();
        Experience exp = new Experience("Software Intern", "Amazon", "NewYork", "May 9", "July 15");
        exp.addResponsibility("Coding");
        exps.add(exp);
        assertEquals(resume.getExperiences().get(0).getEmployer(), exp.getEmployer());
    }
}
