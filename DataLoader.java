import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

    /**
     * loads the list students from the student.json file by parsing through each of their attributes
     * @return an arraylist of students from the json file
     */
    public static ArrayList<Student> loadStudents() { 
        //  create list of Students
        ArrayList<Student> students = new ArrayList<Student>();

        try { 
            //  read students array from JSON
            FileReader studentReader = new FileReader(STUDENT_FILE_NAME);
            JSONParser studentParser = new JSONParser();
            JSONArray studentsJSON = (JSONArray)studentParser.parse(studentReader);

            //  parse everything in student list
            if(studentsJSON != null) { 
                for(int i = 0; i < studentsJSON.size(); i++) { 
                    JSONObject studentJSON = (JSONObject)studentsJSON.get(i);
                    String lastName = (String)studentJSON.get(STUDENT_LASTNAME);
                    String firstName = (String)studentJSON.get(STUDENT_FIRSTNAME);
                    String password = (String)studentJSON.get(STUDENT_PASSWORD);
                    String phoneNumber = (String)studentJSON.get(STUDENT_PHONE);
                    UUID studentID = UUID.fromString((String)studentJSON.get(STUDENT_ID));
                    String major = (String)studentJSON.get(STUDENT_MAJOR);
                    String minor = (String)studentJSON.get(STUDENT_MINOR);
                    String classStanding = (String)studentJSON.get(STUDENT_CLASS_STANDING);
                    String email = (String)studentJSON.get(STUDENT_EMAIL);

                    //  get applied listings ids
                    JSONArray appliedListingsJSON = (JSONArray)studentJSON.get(STUDENT_APPLIED_LISTINGS);
                    ArrayList<UUID> appliedListings = new ArrayList<UUID>();
                    if(appliedListingsJSON != null) { 
                        for(int j = 0; j < appliedListingsJSON.size(); j++) { 
                            appliedListings.add(UUID.fromString((String)appliedListingsJSON.get(j)));
                        }
                    }

                    // get reviews
                    JSONArray reviewsJSON = (JSONArray)studentJSON.get(STUDENT_REVIEWS);
                    ArrayList<Review> reviews = new ArrayList<Review>();
                    if (reviewsJSON != null) {
                        for (int l = 0; l < reviewsJSON.size(); l++) {
                            JSONObject reviewJO = (JSONObject)reviewsJSON.get(l);
                            int rating = (int)(long)reviewJO.get(REVIEW_RATING);
                            String author = (String)reviewJO.get(REVIEW_AUTHOR);
                            String date = (String)reviewJO.get(REVIEW_DATE);
                            String comment = (String)reviewJO.get(REVIEW_COMMENT);
                            Review review = new Review(rating, date, comment, author);
                            reviews.add(review);
                        }
                    }

                    //  get resume 
                    JSONObject resumeJSON = (JSONObject)studentJSON.get(STUDENT_RESUME);
                    String name = (String)resumeJSON.get(RESUME_NAME);
                    String hometown = (String)resumeJSON.get(RESUME_HOMETOWN);

                    //  get education list
                    JSONObject educationJSON = (JSONObject)resumeJSON.get(RESUME_EDUCATION);
                    Education education = null;
                    if(educationJSON != null) { 
                            JSONObject educationJO = (JSONObject)educationJSON;
                            String school = (String)educationJO.get(RESUME_SCHOOL);
                            String resumeMajor = (String)educationJO.get(RESUME_MAJOR);
                            String resumeMinor = (String)educationJO.get(RESUME_MINOR);
                            double gpa = ((Double)educationJO.get(RESUME_GPA)).doubleValue();
                            int graduationYear = ((Long)educationJO.get(RESUME_GRADUATION_YEAR)).intValue();
                            String eduClassStanding = (String)educationJO.get(RESUME_CLASS_STANDING);
                            
                            //  get coursework
                            JSONArray relevenatCourseworkJSON = (JSONArray)educationJO.get(RESUME_RELEVANT_COURSEWORK);
                            ArrayList<String> relevantCoursework = new ArrayList<String>();
                            if(relevantCoursework != null) { 
                                for(int k = 0; k < relevenatCourseworkJSON.size(); k++) { 
                                    relevantCoursework.add((String)relevenatCourseworkJSON.get(k));
                                }
                            }
                        
                            education = new Education(school, resumeMajor, resumeMinor, gpa, graduationYear, eduClassStanding, relevantCoursework);
                            
                        }
                    

                    
                    //  get awards
                    JSONArray awardsJSON = (JSONArray)resumeJSON.get(RESUME_AWARDS);
                    ArrayList<String> awards = new ArrayList<String>();
                    if(awardsJSON != null) { 
                        for(int j = 0; j < awardsJSON.size(); j++) { 
                            awards.add((String)awardsJSON.get(j));
                        }
                    }

                    //  get scholarships
                    JSONArray scholarshipsJSON = (JSONArray)resumeJSON.get(RESUME_SCHOLARSHIPS);
                    ArrayList<String> scholarships = new ArrayList<String>();
                    if(scholarshipsJSON != null) { 
                        for(int j = 0; j < scholarshipsJSON.size(); j++) { 
                            scholarships.add((String)scholarshipsJSON.get(j));
                        }
                    }

                    //  get experiences
                    JSONArray experienceJSON = (JSONArray)resumeJSON.get(RESUME_EXPERIENCES);
                    ArrayList<Experience> experiences = new ArrayList<Experience>();
                    if(experienceJSON != null) {
                        for(int j = 0; j < experienceJSON.size(); j++) { 
                            JSONObject experienceJO = (JSONObject)experienceJSON.get(j);
                            String title = (String)experienceJO.get(RESUME_TITLE);
                            String employer = (String)experienceJO.get(RESUME_EMPLOYER);
                            String location = (String)experienceJO.get(RESUME_LOCATION);
                            String startDate = (String)experienceJO.get(RESUME_START_DATE);
                            String endDate = (String)experienceJO.get(RESUME_END_DATE);

                            //  get responsibilities
                            JSONArray responsibilitiesJSON = (JSONArray)experienceJO.get(RESUME_RESPONSIBILITIES);
                            ArrayList<String> responsibilities = new ArrayList<String>(); 
                                for(int k = 0; k < responsibilitiesJSON.size(); k++) { 
                                    responsibilities.add((String)responsibilitiesJSON.get(k));
                            }
                            //add new experience
                            Experience experience = new Experience(title, employer, location, startDate, endDate, responsibilities);
                            experiences.add(experience);
                        }

                        
                    }
                    Resume resume = new Resume(name, phoneNumber, hometown, education, awards, scholarships, experiences);
                    Student student = new Student(firstName, lastName, email, password, phoneNumber, studentID, major, minor, classStanding, appliedListings, resume);
                    students.add(student);
                    student.setReviews(reviews);
                }
            return students;

        }} catch(Exception e) { 
            e.printStackTrace();
        }
        return students;
    }

    /**
     * parses through the student.json file to get the list of employers and their attributes
     * @return an arraylist of the employers
     */
    public static ArrayList<Employer> loadEmployers() { 
        ArrayList<Employer> employers = new ArrayList<Employer>();
        
        try {
            FileReader employerReader = new FileReader(EMPLOYER_FILE_NAME);
            JSONParser employerParser = new JSONParser();
            JSONArray employersJSON = (JSONArray)employerParser.parse(employerReader);

            if(employersJSON != null) { 
                for(int i =0; i < employersJSON.size(); i++) { 
                    JSONObject employerJSON = (JSONObject)employersJSON.get(i);
                    String lastName = (String)employerJSON.get(EMPLOYER_LASTNAME);
                    String firstName = (String)employerJSON.get(EMPLOYER_FIRSTNAME);
                    UUID employerID = UUID.fromString((String)employerJSON.get(EMPLOYER_ID));
                    String company = (String)employerJSON.get(EMPLOYER_COMPANY);
                    String companyMission = (String)employerJSON.get(EMPLOYER_COMPANYMISSION);
                    String password = (String)employerJSON.get(EMPLOYER_PASSWORD);
                    String email = (String)employerJSON.get(EMPLOYER_EMAIL);

                    //  get listing ids
                    JSONArray listingsJSON = (JSONArray)employerJSON.get(EMPLOYER_LISTINGS);
                    ArrayList<UUID> listings = new ArrayList<UUID>();
                    if(listingsJSON != null) { 
                        for(int j = 0; j < listingsJSON.size(); j++) { 
                            listings.add(UUID.fromString((String)listingsJSON.get(j)));
                        }
                    }
                    employers.add(new Employer(firstName, lastName, employerID, company, companyMission, password, email, listings));
                }
            }

            return employers;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return employers;
    }

    /**
     * parses through a list of Administrators from the administrator.json file and their attributes
     * @return the list of administrators
     */
    public static ArrayList<Administrator> loadAdministrators() { 
        ArrayList<Administrator> administrators = new ArrayList<Administrator>();

        try {
            FileReader adminReader = new FileReader(ADMIN_FILE_NAME);
            JSONParser adminParser = new JSONParser();
            JSONArray adminsJSON = (JSONArray)adminParser.parse(adminReader);

            for(int i = 0; i < adminsJSON.size(); i++) { 
                JSONObject adminJSON = (JSONObject)adminsJSON.get(i);
                String lastName = (String)adminJSON.get(ADMIN_LASTNAME);
                String firstName = (String)adminJSON.get(ADMIN_FIRSTNAME);
                UUID id = UUID.fromString((String)adminJSON.get(ADMIN_ID));
                String password = (String)adminJSON.get(ADMIN_PASSWORD);
                String email = (String)adminJSON.get(ADMIN_EMAIL);
                String jobTitle = (String)adminJSON.get(ADMIN_JOBTITLE);

                Administrator admin = new Administrator(firstName, lastName, email, password, jobTitle);
                admin.setID(id);
                administrators.add(admin);
            }
            return administrators;
        } catch (Exception e) { 
            e.printStackTrace();
        }

        return administrators;
    }

    /**
     * parses through the list of internships from the internshiplisting.json file
     * @return an arraylist of the internship listings
     */
    public static ArrayList<InternshipListing> loadInternshipList() { 
        ArrayList<InternshipListing> internshipList = new ArrayList<InternshipListing>();

        try { 
            FileReader listingReader = new FileReader(LISTING_FILE_NAME);
            JSONParser listingParser = new JSONParser();
            JSONArray listingsJSON = (JSONArray)listingParser.parse(listingReader);

            if(listingsJSON != null) {
                for(int i = 0; i < listingsJSON.size(); i++) { 
                    JSONObject listingJSON = (JSONObject)listingsJSON.get(i);
                    UUID listingID = UUID.fromString((String)listingJSON.get(LISTING_ID));
                    UUID employerID = UUID.fromString((String)listingJSON.get(LISTING_EMPLOYER_ID));
                    String title = (String)listingJSON.get(LISTING_TITLE);
                    String company = (String)listingJSON.get(LISTING_COMPANY);
                    boolean status = (Boolean)listingJSON.get(LISTING_STATUS);
                    String location = (String)listingJSON.get(LISTING_LOCATION);
                    String duration = (String)listingJSON.get(LISTING_DURATION);
                    double lowerPay = ((Double)listingJSON.get(LISTING_LOWER_PAY)).doubleValue();
                    double higherPay = ((Double)listingJSON.get(LISTING_HIGHER_PAY)).doubleValue();
                    ArrayList<String> duties = (ArrayList<String>)listingJSON.get(LISTING_DUTIES);
                    
                    //  get requirements
                    JSONArray requirementsJSON = (JSONArray)listingJSON.get(LISTING_REQUIREMENTS);
                    ArrayList<String> requirements = new ArrayList<String>();
                    if(requirementsJSON != null) { 
                        for(int j = 0; j < requirementsJSON.size(); j++) { 
                            requirements.add((String)requirementsJSON.get(j));
                        }
                    }

                    //String applicationCloseDate = (String)listingJSON.get(LISTING_APPLICATION_CLOSE_DATE);
                    //String startDate = (String)listingJSON.get(LISTING_STARTING_DATE);

                    //  get applicants
                    JSONArray applicantsJSON = (JSONArray)listingJSON.get(LISTING_APPLICANTS);
                    ArrayList<UUID> applicants = new ArrayList<UUID>();
                    if(applicantsJSON != null) { 
                        for(int j = 0; j < applicantsJSON.size(); j++) { 
                            applicants.add(UUID.fromString((String)applicantsJSON.get(j)));
                        }
                    }

                    UUID studentID = null;
                    if (listingJSON.get(LISTING_STUDENT_ID) != null)
                        studentID = UUID.fromString((String)listingJSON.get(LISTING_STUDENT_ID));

                    internshipList.add(new InternshipListing(title, company, status, listingID, employerID, location, duration, lowerPay, higherPay, duties, requirements, applicants, studentID));
                }
            }
                
                return internshipList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return internshipList;
    }

}