public class VisitorDesignPattern {
	interface PatientVisitor {
		void visit(InPatient patient);
		void visit(OutPatient patient);
	}

	abstract static class Patient {
		private final String id;
		private final String name;
		private final String phoneNumber;

		Patient(String id, String name, String phoneNumber) {
			if (id == null || id.isBlank()) {
				throw new IllegalArgumentException("Patient id cannot be empty");
			}
			if (name == null || name.isBlank()) {
				throw new IllegalArgumentException("Patient name cannot be empty");
			}
			if (phoneNumber == null || phoneNumber.isBlank()) {
				throw new IllegalArgumentException("Phone number cannot be empty");
			}
			this.id = id;
			this.name = name;
			this.phoneNumber = phoneNumber;
		}

		public abstract void accept(PatientVisitor visitor);

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}
	}

	static class InPatient extends Patient {
		private final int roomNumber;
		private final String attendingDoctor;

		InPatient(String id, String name, String phoneNumber, int roomNumber, String attendingDoctor) {
			super(id, name, phoneNumber);
			if (roomNumber <= 0) {
				throw new IllegalArgumentException("Room number must be positive");
			}
			this.roomNumber = roomNumber;
			this.attendingDoctor = attendingDoctor;
		}

		@Override
		public void accept(PatientVisitor visitor) {
			visitor.visit(this);
		}

		public int getRoomNumber() {
			return roomNumber;
		}

		public String getAttendingDoctor() {
			return attendingDoctor;
		}
	}

	static class OutPatient extends Patient {
		private final String department;
		private final String consultationType;

		OutPatient(String id, String name, String phoneNumber, String department, String consultationType) {
			super(id, name, phoneNumber);
			this.department = department;
			this.consultationType = consultationType;
		}

		@Override
		public void accept(PatientVisitor visitor) {
			visitor.visit(this);
		}

		public String getDepartment() {
			return department;
		}

		public String getConsultationType() {
			return consultationType;
		}
	}

	static class HospitalRegistry {
		private final java.util.List<Patient> patients = new java.util.ArrayList<>();

		public void addPatient(Patient patient) {
			if (patient == null) {
				throw new IllegalArgumentException("Patient cannot be null");
			}
			patients.add(patient);
		}

		public void process(PatientVisitor visitor) {
			for (Patient patient : patients) {
				patient.accept(visitor);
			}
		}

		public int size() {
			return patients.size();
		}
	}

	static class BillingVisitor implements PatientVisitor {
		private double totalBill = 0;

		@Override
		public void visit(InPatient patient) {
			double bill = 250.0;
			totalBill += bill;
			System.out.println("Billing: In-patient " + patient.getName()
					+ " (Room " + patient.getRoomNumber() + ") owes $" + bill + ".");
		}

		@Override
		public void visit(OutPatient patient) {
			double bill = patient.getConsultationType().equalsIgnoreCase("Cardiology") ? 150.0 : 80.0;
			totalBill += bill;
			System.out.println("Billing: Out-patient " + patient.getName()
					+ " for " + patient.getConsultationType() + " owes $" + bill + ".");
		}

		public double getTotalBill() {
			return totalBill;
		}
	}

	static class DiagnosisVisitor implements PatientVisitor {
		@Override
		public void visit(InPatient patient) {
			System.out.println("Doctor: " + patient.getName() + " admitted in room "
					+ patient.getRoomNumber() + ". Attending doctor: " + patient.getAttendingDoctor());
		}

		@Override
		public void visit(OutPatient patient) {
			System.out.println("Doctor: " + patient.getName() + " is an out-patient in "
					+ patient.getDepartment() + " for " + patient.getConsultationType() + ".");
		}
	}

	static class DischargeVisitor implements PatientVisitor {
		@Override
		public void visit(InPatient patient) {
			System.out.println("Discharge team: " + patient.getName()
					+ " can be discharged after final review from room " + patient.getRoomNumber() + ".");
		}

		@Override
		public void visit(OutPatient patient) {
			System.out.println("Discharge team: " + patient.getName()
					+ " has completed the " + patient.getConsultationType() + " consultation and can leave.");
		}
	}

	public static void main(String[] args) {
		HospitalRegistry registry = new HospitalRegistry();
		registry.addPatient(new InPatient("P-101", "Alice", "9876543210", 201, "Dr. Mehta"));
		registry.addPatient(new OutPatient("P-102", "Bob", "9988776655", "Cardiology", "Cardiology"));
		registry.addPatient(new InPatient("P-103", "Charlie", "9123456789", 305, "Dr. Sharma"));

		BillingVisitor billingVisitor = new BillingVisitor();
		DiagnosisVisitor diagnosisVisitor = new DiagnosisVisitor();
		DischargeVisitor dischargeVisitor = new DischargeVisitor();

		System.out.println("=== Hospital patient processing ===");
		registry.process(billingVisitor);
		registry.process(diagnosisVisitor);
		registry.process(dischargeVisitor);

		System.out.println("\nTotal bills generated: $" + billingVisitor.getTotalBill());
	}
}
