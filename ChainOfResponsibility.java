public class ChainOfResponsibility {
	interface LeaveHandler {
		void setNext(LeaveHandler nextHandler);
		void approveLeave(LeaveRequest request);
	}

	static class LeaveRequest {
		private final String employeeName;
		private final int days;

		LeaveRequest(String employeeName, int days) {
			this.employeeName = employeeName;
			this.days = days;
		}

		String getEmployeeName() {
			return employeeName;
		}

		int getDays() {
			return days;
		}
	}

	abstract static class AbstractLeaveHandler implements LeaveHandler {
		private LeaveHandler nextHandler;

		@Override
		public void setNext(LeaveHandler nextHandler) {
			this.nextHandler = nextHandler;
		}

		protected void forwardToNext(LeaveRequest request) {
			if (nextHandler != null) {
				nextHandler.approveLeave(request);
			} else {
				System.out.println("No further approval level available. Request cannot be approved.");
			}
		}
	}

	static class TeamLeadHandler extends AbstractLeaveHandler {
		@Override
		public void approveLeave(LeaveRequest request) {
			if (request.getDays() <= 2) {
				System.out.println("Team Lead approved leave for " + request.getEmployeeName()
						+ " for " + request.getDays() + " day(s).");
			} else {
				System.out.println("Team Lead cannot approve more than 2 days. Forwarding to Manager...");
				forwardToNext(request);
			}
		}
	}

	static class ManagerHandler extends AbstractLeaveHandler {
		@Override
		public void approveLeave(LeaveRequest request) {
			if (request.getDays() <= 7) {
				System.out.println("Manager approved leave for " + request.getEmployeeName()
						+ " for " + request.getDays() + " day(s).");
			} else {
				System.out.println("Manager cannot approve more than 7 days. Forwarding to HR...");
				forwardToNext(request);
			}
		}
	}

	static class HRHandler extends AbstractLeaveHandler {
		@Override
		public void approveLeave(LeaveRequest request) {
			if (request.getDays() <= 15) {
				System.out.println("HR approved leave for " + request.getEmployeeName()
						+ " for " + request.getDays() + " day(s).");
			} else {
				System.out.println("Leave request for " + request.getEmployeeName() + " for "
						+ request.getDays() + " day(s) has been rejected by HR.");
			}
		}
	}

	public static void main(String[] args) {
		TeamLeadHandler teamLead = new TeamLeadHandler();
		ManagerHandler manager = new ManagerHandler();
		HRHandler hr = new HRHandler();

		teamLead.setNext(manager);
		manager.setNext(hr);

		System.out.println("=== Leave approval chain demo ===");
		teamLead.approveLeave(new LeaveRequest("Alice", 1));
		teamLead.approveLeave(new LeaveRequest("Bob", 5));
		teamLead.approveLeave(new LeaveRequest("Charlie", 10));
		teamLead.approveLeave(new LeaveRequest("David", 20));
	}
}
