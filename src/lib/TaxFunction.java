package lib;

public class TaxFunction {

	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 *
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan
	 * (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan),
	 * dikurangi penghasilan tidak kena pajak (PTKP).
	 *
	 * PTKP dasar: Rp 54.000.000
	 * Tambahan jika menikah: Rp 4.500.000
	 * Tambahan per anak (maks 3 anak): Rp 4.500.000 per anak
	 */
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 months working per year");
			numberOfMonthWorking = 12;
		}

		numberOfChildren = Math.min(numberOfChildren, 3);

		final int BASE_PTKP = 54000000;
		final int MARRIED_PTKP = 4500000;
		final int CHILD_PTKP = 4500000;

		int ptkp = BASE_PTKP;
		if (isMarried) {
			ptkp += MARRIED_PTKP;
		}
		ptkp += numberOfChildren * CHILD_PTKP;

		int annualIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
		int taxableIncome = annualIncome - deductible - ptkp;

		if (taxableIncome <= 0) {
			return 0;
		}

		return (int) Math.round(0.05 * taxableIncome);
	}
}
