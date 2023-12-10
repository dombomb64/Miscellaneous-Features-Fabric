package net.db64.miscfeatures.util;

public class ExtraMath {
	public static float average(float value1, float value2) {
		return (value1 + value2) / 2;
	}

	public static float average(float... values) {
		float result = 0;
		for (float value : values) {
			result += value;
		}
		return result / values.length;
	}

	public static float clamp(float value, float min, float max) {
		return Math.max(min, Math.min(max, value));
	}

	public static float lerp(float start, float end, float interpolation) {
		return start * (1.0f - interpolation) + (end * interpolation);
	}

	public static float lerpFast(float start, float end, float interpolation) {
		return start + interpolation * (end - start);
	}
}
