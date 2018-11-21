
package org.drip.spline.bspline;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * Copyright (C) 2015 Lakshmi Krishnamurthy
 * Copyright (C) 2014 Lakshmi Krishnamurthy
 * Copyright (C) 2013 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting risk, transaction costs, exposure, margin
 *  	calculations, and portfolio construction within and across fixed income, credit, commodity, equity,
 *  	FX, and structured products.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three main modules:
 *  
 *  - DROP Analytics Core - https://lakshmidrip.github.io/DROP-Analytics-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Numerical Core - https://lakshmidrip.github.io/DROP-Numerical-Core/
 * 
 * 	DROP Analytics Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Asset Backed Analytics
 * 	- XVA Analytics
 * 	- Exposure and Margin Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Numerical Core implements libraries for the following:
 * 	- Statistical Learning Library
 * 	- Numerical Optimizer Library
 * 	- Machine Learning Library
 * 	- Spline Builder Library
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
 * 	- Javadoc                  => https://lakshmidrip.github.io/DROP/Javadoc/index.html
 * 	- Technical Specifications => https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal
 * 	- Release Versions         => https://lakshmidrip.github.io/DROP/version.html
 * 	- Community Credits        => https://lakshmidrip.github.io/DROP/credits.html
 * 	- Issues Catalog           => https://github.com/lakshmiDRIP/DROP/issues
 * 	- JUnit                    => https://lakshmidrip.github.io/DROP/junit/index.html
 * 	- Jacoco                   => https://lakshmidrip.github.io/DROP/jacoco/index.html
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
 * <i>SegmentMulticBasisFunction</i> implements the local quadratic B Spline that envelopes the predictor
 * ordinates, and the corresponding set of ordinates/basis functions. SegmentMulticBasisFunction uses the
 * left/right SegmentBasisFunction instances to achieve its implementation goals.
 *
 * <br><br>
 *  <ul>
 *		<li><b>Project</b>       = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/spline">Spaces</a></li>
 *		<li><b>Package</b>       = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/spline/bspline">B Spline</a></li>
 *		<li><b>Specification</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal/SplineBuilder">Spline Builder Library</a></li>
 *  </ul>
 * <br><br>
 *
 * @author Lakshmi Krishnamurthy
 */

public class SegmentMulticBasisFunction extends org.drip.spline.bspline.SegmentBasisFunction {
	private org.drip.spline.bspline.SegmentBasisFunction _oeLeft = null;
	private org.drip.spline.bspline.SegmentBasisFunction _oeRight = null;

	/**
	 * SegmentMulticBasisFunction constructor
	 * 
	 * @param oeLeft Left Ordered Envelope Spline Function
	 * @param oeRight Right Ordered Envelope Spline Function
	 * 
	 * @throws java.lang.Exception Thrown if Inputs are invalid
	 */

	public SegmentMulticBasisFunction (
		final org.drip.spline.bspline.SegmentBasisFunction oeLeft,
		final org.drip.spline.bspline.SegmentBasisFunction oeRight)
		throws java.lang.Exception
	{
		super (oeLeft.bSplineOrder() + 1, oeLeft.leading(), oeRight.leading(), oeRight.trailing());

		if ((_oeLeft = oeLeft).bSplineOrder() != (_oeRight = oeRight).bSplineOrder())
			throw new java.lang.Exception ("SegmentMulticBasisFunction ctr: Invalid Inputs");
	}

	@Override public double evaluate (
		final double dblPredictorOrdinate)
		throws java.lang.Exception
	{
		if (!org.drip.quant.common.NumberUtil.IsValid (dblPredictorOrdinate))
			throw new java.lang.Exception ("SegmentMulticBasisFunction::evaluate => Invalid Inputs");

		if (dblPredictorOrdinate < leading() || dblPredictorOrdinate > trailing()) return 0.;

		return _oeLeft.normalizedCumulative (dblPredictorOrdinate) - _oeRight.normalizedCumulative
			(dblPredictorOrdinate);
	}

	@Override public double integrate (
		final double dblBegin,
		final double dblEnd)
		throws java.lang.Exception
	{
		return org.drip.quant.calculus.R1ToR1Integrator.Simpson (this, dblBegin, dblEnd);
	}

	@Override public double normalizer()
		throws java.lang.Exception
	{
		return integrate (leading(), trailing());
	}

	@Override public double normalizedCumulative (
		final double dblPredictorOrdinate)
		throws java.lang.Exception
	{
		if (!org.drip.quant.common.NumberUtil.IsValid (dblPredictorOrdinate))
			throw new java.lang.Exception
				("SegmentMulticBasisFunction::normalizedCumulative => Invalid Inputs");

		if (dblPredictorOrdinate < leading()) return 0.;

		if (dblPredictorOrdinate > trailing()) return 1.;

		return integrate (leading(), dblPredictorOrdinate) / normalizer();
	}
}
