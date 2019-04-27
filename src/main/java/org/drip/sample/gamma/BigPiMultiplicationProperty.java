
package org.drip.sample.gamma;

import org.drip.function.definition.R1ToR1Property;
import org.drip.function.definition.R1PropertyVerification;
import org.drip.numerical.common.FormatUtil;
import org.drip.service.env.EnvManager;
import org.drip.specialfunction.property.BigPiEqualityLemma;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2019 Lakshmi Krishnamurthy
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
 * <i>BigPiMultiplicationProperty</i> demonstrates the Verification of the Multiplication Property of the Big
 * Pi Function. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Blagouchine, I. V. (2014): Re-discovery of Malmsten's Integrals, their Evaluation by Contour
 * 				Integration Methods, and some Related Results <i>Ramanujan Journal</i> <b>35 (1)</b> 21-110
 * 		</li>
 * 		<li>
 * 			Borwein, J. M., and R. M. Corless (2017): Gamma Function and the Factorial in the Monthly
 * 				https://arxiv.org/abs/1703.05349 <b>arXiv</b>
 * 		</li>
 * 		<li>
 * 			Davis, P. J. (1959): Leonhard Euler's Integral: A Historical Profile of the Gamma Function
 * 				<i>American Mathematical Monthly</i> <b>66 (10)</b> 849-869
 * 		</li>
 * 		<li>
 * 			Whitaker, E. T., and G. N. Watson (1996): <i>A Course on Modern Analysis</i> <b>Cambridge
 * 				University Press</b> New York
 * 		</li>
 * 		<li>
 * 			Wikipedia (2019): Gamma Function https://en.wikipedia.org/wiki/Gamma_function
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalCore.md">Numerical Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalOptimizerLibrary.md">Numerical Optimizer</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">Function</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/gamma/README.md">Integrand Estimates of Gamma Functions</a></li>
 *  </ul>
 *
 * @author Lakshmi Krishnamurthy
 */

public class BigPiMultiplicationProperty
{

	private static final void Verifier (
		final int m,
		final double[] sArray,
		final R1ToR1Property bigPiMultiplicationProperty)
		throws Exception
	{
		System.out.println ("\t|----------------------------------------------||");

		System.out.println ("\t|    BIG PI FUNCTION MULTIPLICATION PROPERTY   ||");

		System.out.println ("\t|                  m = " + FormatUtil.FormatDouble (m, 1, 0, 1.));

		System.out.println ("\t|----------------------------------------------||");

		System.out.println ("\t|        L -> R:                               ||");

		System.out.println ("\t|                - s                           ||");

		System.out.println ("\t|                - LHS Value                   ||");

		System.out.println ("\t|                - RHS Value                   ||");

		System.out.println ("\t|                - Verification Success?       ||");

		System.out.println ("\t|----------------------------------------------||");

		for (double s : sArray)
		{
			R1PropertyVerification propertyVerification = bigPiMultiplicationProperty.verify (s);

			System.out.println (
				"\t|" + FormatUtil.FormatDouble (s, 1, 2, 1.) + " => " +
					FormatUtil.FormatDouble (propertyVerification.lValue(), 1, 10, 1.) + " | " +
					FormatUtil.FormatDouble (propertyVerification.rValue(), 1, 10, 1.) + " | " +
					propertyVerification.verified() + " ||"
			);
		}

		System.out.println ("\t|----------------------------------------------||");

		System.out.println();
	}

	public static final void main (
		final String[] argumentArray)
		throws Exception
	{
		EnvManager.InitEnv ("");

		int[] mArray =
		{
			3,
			4,
			5,
			6,
			7,
			8,
			9,
		};
		double[] zArray =
		{
			-0.95,
			-0.90,
			-0.85,
			-0.80,
			-0.75,
			-0.70,
			-0.65,
			-0.60,
			-0.55,
			-0.50,
			-0.40,
			-0.30,
			-0.20,
			-0.10,
		};

		for (int m : mArray)
		{
			Verifier (
				m,
				zArray,
				BigPiEqualityLemma.MultiplicationFormula (m)
			);
		}

		EnvManager.TerminateEnv();
	}
}
