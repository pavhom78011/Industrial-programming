
public class JavaDeveloper implements Collection {
    private String name;
    private String[] skills;
    private String[] projects;

    public String[] getProjects() {
        return projects;
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    JavaDeveloper (String name, String[] skills, String[] projects) {
        this.name = name;
        this.skills = skills;
        this.projects = projects;
    }

    @Override
    public Iterator getIterator() {
        return new SkillProjectIterator();
    }

    private class SkillProjectIterator implements Iterator {
        int index;
        int projectIndex;

        @Override
        public boolean hasNext() {
            return index < skills.length || projectIndex < projects.length;
        }

        @Override
        public Object next() {
            if (index < skills.length) {
                return skills[index++];
            }
            else {
                return projects[projectIndex++];
            }
        }
    }
}
