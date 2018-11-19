
package org.drip.simm.margin;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * 
 *  This file is part of DRIP, a free-software/open-source library for buy/side financial/trading model
 *  	libraries targeting analysts and developers
 *  	https://lakshmidrip.github.io/DRIP/
 *  
 *  DRIP is composed of four main libraries:
 *  
 *  - DRIP Fixed Income - https://lakshmidrip.github.io/DRIP-Fixed-Income/
 *  - DRIP Asset Allocation - https://lakshmidrip.github.io/DRIP-Asset-Allocation/
 *  - DRIP Numerical Optimizer - https://lakshmidrip.github.io/DRIP-Numerical-Optimizer/
 *  - DRIP Statistical Learning - https://lakshmidrip.github.io/DRIP-Statistical-Learning/
 * 
 *  - DRIP Fixed Income: Library for Instrument/Trading Conventions, Treasury Futures/Options,
 *  	Funding/Forward/Overnight Curves, Multi-Curve Construction/Valuation, Collateral Valuation and XVA
 *  	Metric Generation, Calibration and Hedge Attributions, Statistical Curve Construction, Bond RV
 *  	Metrics, Stochastic Evolution and Option Pricing, Interest Rate Dynamics and Option Pricing, LMM
 *  	Extensions/Calibrations/Greeks, Algorithmic Differentiation, and Asset Backed Models and Analytics.
 * 
 *  - DRIP Asset Allocation: Library for model libraries for MPT framework, Black Litterman Strategy
 *  	Incorporator, Holdings Constraint, and Transaction Costs.
 * 
 *  - DRIP Numerical Optimizer: Library for Numerical Optimization and Spline Functionality.
 * 
 *  - DRIP Statistical Learning: Library for Statistical Evaluation and Machine Learning.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   	you may not use this file except in compliance with the License.
 *   
 *  You may obtain a copy of the License at
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  	distributed under the License is distributed on an "AS IS" BASIS,
 *  	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  
 *  See the License for the specific language governing permissions and
 *  	limitations under the License.
 */

/**
 * <i>SensitivityAggregateCR</i> holds the IM Margin Sensitivity Co-variances within a single Bucket for each
 * of the CR Component Risk Factors. The References are:
 * 
 * <br><br>
 *  <ul>
 *  	<li>
 *  		Andersen, L. B. G., M. Pykhtin, and A. Sokol (2017): Credit Exposure in the Presence of Initial
 *  			Margin https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2806156 <b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		Albanese, C., S. Caenazzo, and O. Frankel (2017): Regression Sensitivities for Initial Margin
 *  			Calculations https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2763488 <b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		Anfuso, F., D. Aziz, P. Giltinan, and K. Loukopoulus (2017): A Sound Modeling and Back-testing
 *  			Framework for Forecasting Initial Margin Requirements
 *  				https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2716279 <b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		Caspers, P., P. Giltinan, R. Lichters, and N. Nowaczyk (2017): Forecasting Initial Margin
 *  			Requirements - A Model Evaluation https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2911167
 *  				<b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		International Swaps and Derivatives Association (2017): SIMM v2.0 Methodology
 *  			https://www.isda.org/a/oFiDE/isda-simm-v2.pdf
 *  	</li>
 *  </ul>
 * 
 * <br><br>
 *  <ul>
 *		<li><b>Project</b>       = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/simm">SIMM</a></li>
 *		<li><b>Package</b>       = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/simm/margin">Margin</a></li>
 *		<li><b>Specification</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal/NumericalOptimizer">Numerical Optimizer Library</a></li>
 *  </ul>
 * <br><br>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class SensitivityAggregateCR
{
	private double _cumulativeMarginSensitivity = java.lang.Double.NaN;
	private java.util.Map<java.lang.String, java.lang.Double> _componentMarginCovarianceMap = null;

	/**
	 * SensitivityAggregateCR Constructor
	 * 
	 * @param componentMarginCovarianceMap The Component Margin Co-variance Map
	 * @param cumulativeMarginSensitivity The Cumulative Margin Sensitivity
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public SensitivityAggregateCR (
		final java.util.Map<java.lang.String, java.lang.Double> componentMarginCovarianceMap,
		final double cumulativeMarginSensitivity)
		throws java.lang.Exception
	{
		if (null == (_componentMarginCovarianceMap = componentMarginCovarianceMap) ||
				0 == _componentMarginCovarianceMap.size() ||
			!org.drip.quant.common.NumberUtil.IsValid (_cumulativeMarginSensitivity =
				cumulativeMarginSensitivity))
		{
			throw new java.lang.Exception ("SensitivityAggregateCR Constructor => Invalid Inputs");
		}
	}

	/**
	 * Retrieve the Component Margin Covariance Map
	 * 
	 * @return The Component Margin Covariance Map
	 */

	public java.util.Map<java.lang.String, java.lang.Double> componentMarginCovarianceMap()
	{
		return _componentMarginCovarianceMap;
	}

	/**
	 * Compute the Cumulative Margin Covariance
	 * 
	 * @return The Cumulative Margin Covariance
	 */

	public double cumulativeMarginCovariance()
	{
		double cumulativeMarginCovariance = 0.;

		for (java.util.Map.Entry<java.lang.String, java.lang.Double> componentMarginCovarianceEntry :
			_componentMarginCovarianceMap.entrySet())
		{
			cumulativeMarginCovariance = cumulativeMarginCovariance +
				componentMarginCovarianceEntry.getValue();
		}

		return cumulativeMarginCovariance;
	}

	/**
	 * Compute the Cumulative Sensitivity Margin
	 * 
	 * @return The Cumulative Sensitivity Margin
	 */

	public double cumulativeMargin()
	{
		return java.lang.Math.sqrt (cumulativeMarginCovariance());
	}

	/**
	 * Retrieve the Cumulative Margin Sensitivity
	 * 
	 * @return The Cumulative Margin Sensitivity
	 */

	public double cumulativeMarginSensitivity()
	{
		return _cumulativeMarginSensitivity;
	}
}
