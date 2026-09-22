package es.upm.aled.lab1.measurements;

/**
 * Filter that extracts the specified channels from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractChannels implements Filter {

	private int[] validChannels;

	/**
	 * Builds the Filter. The use from an array of valid channels.
	 * 
	 * @param validChannels The channel numbers to be extracted, starting from 0.
	 */
	public FilterExtractChannels(int[] validChannels) {
		this.validChannels = validChannels;
		
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		Measurement[] medidasOriginales = eeg.getMeasurements();					//array con todas las muestras original
		
		EEGModel eegFiltered = new EEGModel();
		float[] newChannels = new float[this.validChannels.length];

		for(Measurement m : medidasOriginales) {
			for (int i = 0; i<m.numChannels(); i++) {
				for(int j = 0; j<this.validChannels.length; j++) {
					if(i== this.validChannels[j]) {
						newChannels[j] = m.getChannel(i);
					}
				}
			}
			eegFiltered.addMeasurement(new Measurement (newChannels));
			newChannels = new float [this.validChannels.length];
		}
		return  eegFiltered;
	}

}
