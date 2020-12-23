class Person {
    private String firstName, lastName, middleName, country, address, phone, gender;
    private int age;

    public Person(PersonBuilder personBuilder) {
        this.firstName = personBuilder.firstName;
        this.lastName = personBuilder.lastName;
        this.middleName = personBuilder.middleName;
        this.country = personBuilder.country;
        this.address = personBuilder.address;
        this.phone = personBuilder.phone;
        this.gender = personBuilder.gender;
        this.age = personBuilder.age;
    }

    public String getCountry() {
        return country;
    }

    public static class PersonBuilder {
        private String firstName, lastName, middleName, country, address, phone, gender;
        private int age;

        public PersonBuilder addFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder addLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder addMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public PersonBuilder addCountry(String country) {
            this.country = country;
            return this;
        }

        public PersonBuilder addAddress(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder addPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public PersonBuilder addGender(String gender) {
            this.gender = gender;
            return this;
        }

        public PersonBuilder addAge(int age) {
            this.age = age;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}